package com.smartjobportal.job.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.JobMatch;
import com.smartjobportal.entity.Resume;
import com.smartjobportal.entity.User;
import com.smartjobportal.job.dto.RecommendedJobDto;
import com.smartjobportal.job.service.RecommendationService;
import com.smartjobportal.repository.CandidateProfileRepository;
import com.smartjobportal.repository.JobMatchRepository;
import com.smartjobportal.repository.ResumeRepository;
import com.smartjobportal.repository.UserRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	private JobMatchRepository jobMatchRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CandidateProfileRepository candidateProfileRepository;

	@Override
	public List<RecommendedJobDto> getRecommendations() {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		List<Resume> activeResumes = resumeRepository.findByCandidateProfileAndIsActive(candidateProfile, true);

		if (activeResumes.isEmpty()) {

			throw new RuntimeException("No active resume found");
		}

		Resume activeResume = activeResumes.get(0);

		List<JobMatch> matches = jobMatchRepository.findByResumeResumeId(activeResume.getResumeId());

		matches.sort(Comparator.comparing(JobMatch::getMatchPercentage).reversed());

		List<RecommendedJobDto> recommendations = new ArrayList<>();

		for (JobMatch match : matches) {

			if (match.getMatchPercentage() <= 0) {
				continue;
			}

			RecommendedJobDto dto = new RecommendedJobDto();

			dto.setJobId(match.getJob().getJobId());

			dto.setJobTitle(match.getJob().getJobTitle());

			dto.setCompanyName(match.getJob().getCompany().getCompanyName());

			dto.setMatchPercentage(BigDecimal.valueOf(match.getMatchPercentage()));

			dto.setMatchedSkills(match.getMatchedSkills());

			dto.setMissingSkills(match.getMissingSkills());

			recommendations.add(dto);
		}

		return recommendations;
	}

	private CandidateProfile getLoggedInCandidate() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		return candidateProfileRepository.findByUserUserId(user.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));
	}
}