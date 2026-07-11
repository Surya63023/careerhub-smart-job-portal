package com.smartjobportal.job.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.JobApplication;
import com.smartjobportal.entity.JobMatch;
import com.smartjobportal.entity.Resume;
import com.smartjobportal.exception.ResourceNotFoundException;
import com.smartjobportal.job.dto.MatchResultDto;
import com.smartjobportal.job.dto.RecommendedJobDto;
import com.smartjobportal.job.service.JobMatchingService;
import com.smartjobportal.repository.JobApplicationRepository;
import com.smartjobportal.repository.JobMatchRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.ResumeRepository;

@Service
public class JobMatchingServiceImpl implements JobMatchingService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private JobMatchRepository jobMatchRepository;

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Override
	public MatchResultDto matchResumeWithJob(Integer jobId, Integer resumeId) {

		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found with ID : " + jobId));

		Resume resume = resumeRepository.findById(resumeId)
				.orElseThrow(() -> new ResourceNotFoundException("Resume not found with ID : " + resumeId));

		/*
		 * Return existing match if already calculated
		 */
		if (jobMatchRepository.existsByJobAndResume(job, resume)) {

			/*
			 * Delete existing cached match so that resume changes are reflected.
			 */

			JobMatch existingMatch = jobMatchRepository.findByJobAndResume(job, resume);

			if (existingMatch != null) {

				jobMatchRepository.delete(existingMatch);

			}
		}

		/*
		 * Safe handling for NULL required skills
		 */
		String requiredSkillsText = job.getRequiredSkills();

		if (requiredSkillsText == null || requiredSkillsText.isBlank()) {

			requiredSkillsText = "";
		}

		/*
		 * Safe handling for NULL parsed skills
		 */
		String parsedSkillsText = resume.getParsedSkills();

		if (parsedSkillsText == null || parsedSkillsText.isBlank()) {

			parsedSkillsText = "";
		}

		List<String> jobSkills = Arrays.stream(requiredSkillsText.split(",")).map(String::trim)
				.filter(skill -> !skill.isBlank()).map(String::toLowerCase).collect(Collectors.toList());

		List<String> resumeSkills = Arrays.stream(parsedSkillsText.split(",")).map(String::trim)
				.filter(skill -> !skill.isBlank()).map(String::toLowerCase).collect(Collectors.toList());

		List<String> matchedSkills = new ArrayList<>();

		List<String> missingSkills = new ArrayList<>();

		for (String skill : jobSkills) {

			if (resumeSkills.contains(skill)) {

				matchedSkills.add(skill);

			} else {

				missingSkills.add(skill);
			}
		}

		double matchPercentage = 0.0;

		if (!jobSkills.isEmpty()) {

			matchPercentage = ((double) matchedSkills.size() / jobSkills.size()) * 100;
		}

		JobMatch jobMatch = new JobMatch();

		jobMatch.setJob(job);

		jobMatch.setResume(resume);

		jobMatch.setMatchPercentage(matchPercentage);

		jobMatch.setMatchedSkills(String.join(", ", matchedSkills));

		jobMatch.setMissingSkills(String.join(", ", missingSkills));

		jobMatchRepository.save(jobMatch);

		MatchResultDto dto = new MatchResultDto();

		dto.setJobId(jobId);

		dto.setResumeId(resumeId);

		dto.setMatchPercentage(matchPercentage);

		dto.setMatchedSkills(String.join(", ", matchedSkills));

		dto.setMissingSkills(String.join(", ", missingSkills));

		return dto;
	}

	@Override
	public List<MatchResultDto> getJobMatches(Integer jobId) {

		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found with ID : " + jobId));

		List<JobMatch> matches = jobMatchRepository.findByJobOrderByMatchPercentageDesc(job);

		return matches.stream().map(match -> {

			MatchResultDto dto = new MatchResultDto();

			dto.setJobId(match.getJob().getJobId());

			dto.setResumeId(match.getResume().getResumeId());

			dto.setMatchPercentage(match.getMatchPercentage());

			dto.setMatchedSkills(match.getMatchedSkills());

			dto.setMissingSkills(match.getMissingSkills());

			return dto;

		}).collect(Collectors.toList());
	}

	@Override
	public List<RecommendedJobDto> getRecommendedJobs(Integer resumeId) {

		Resume resume = resumeRepository.findById(resumeId)
				.orElseThrow(() -> new ResourceNotFoundException("Resume not found with ID : " + resumeId));

		List<Job> jobs = jobRepository.findByJobStatus("OPEN");

		List<RecommendedJobDto> recommendations = new ArrayList<>();

		for (Job job : jobs) {

			/*
			 * Skip jobs with no configured skills
			 */
			if (job.getRequiredSkills() == null || job.getRequiredSkills().isBlank()) {

				continue;
			}

			MatchResultDto result = matchResumeWithJob(job.getJobId(), resumeId);

			if (result.getMatchPercentage() >= 70) {

				RecommendedJobDto dto = new RecommendedJobDto();

				dto.setJobId(job.getJobId());

				dto.setJobTitle(job.getJobTitle());

				if (job.getCompany() != null) {

					dto.setCompanyName(job.getCompany().getCompanyName());
				}

				dto.setMatchPercentage(java.math.BigDecimal.valueOf(result.getMatchPercentage()));

				recommendations.add(dto);
			}
		}

		recommendations.sort(

				(a, b) -> b.getMatchPercentage().compareTo(a.getMatchPercentage()));

		return recommendations;
	}

	@Override
	public Page<RecommendedJobDto> getRecommendedJobs(Integer resumeId, Pageable pageable) {

		List<RecommendedJobDto> recommendations = getRecommendedJobs(resumeId);

		int start = (int) pageable.getOffset();

		int end = Math.min(start + pageable.getPageSize(), recommendations.size());

		List<RecommendedJobDto> pageContent = recommendations.subList(start, end);

		return new PageImpl<>(pageContent, pageable, recommendations.size());
	}

	@Override
	public List<MatchResultDto> getAppliedCandidateMatches(Integer jobId) {

		Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

		List<JobApplication> applications = jobApplicationRepository.findByJob(job);

		List<MatchResultDto> results = new ArrayList<>();

		for (JobApplication application : applications) {

			Resume resume = application.getResume();

			MatchResultDto matchResult = matchResumeWithJob(jobId, resume.getResumeId());

			matchResult.setApplicationId(application.getApplicationId());

			matchResult.setCandidateName(application.getCandidateProfile().getUser().getFirstName() + " "
					+ application.getCandidateProfile().getUser().getLastName());

			matchResult.setCandidateEmail(application.getCandidateProfile().getUser().getEmail());

			matchResult.setResumeTitle(resume.getResumeTitle());

			matchResult.setFileName(resume.getFileName());

			matchResult.setFilePath(resume.getFilePath());

			results.add(matchResult);
		}

		results.sort((a, b) -> Double.compare(b.getMatchPercentage(), a.getMatchPercentage()));

		return results;
	}
}