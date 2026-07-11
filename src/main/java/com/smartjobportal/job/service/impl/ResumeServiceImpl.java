package com.smartjobportal.job.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.Resume;
import com.smartjobportal.entity.User;

import com.smartjobportal.job.dto.CreateResumeDto;
import com.smartjobportal.job.dto.ResumeResponseDto;
import com.smartjobportal.job.dto.UpdateResumeDto;
import com.smartjobportal.job.mapper.ResumeMapper;
import com.smartjobportal.job.service.ResumeService;
import com.smartjobportal.job.service.ResumeUploadService;
import com.smartjobportal.repository.CandidateProfileRepository;
import com.smartjobportal.repository.JobApplicationRepository;
import com.smartjobportal.repository.JobMatchRepository;
import com.smartjobportal.repository.ResumeRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.job.service.JobMatchingService;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private CandidateProfileRepository candidateProfileRepository;

	@Autowired
	private JobMatchRepository jobMatchRepository;
	
	@Autowired
	private JobMatchingService jobMatchingService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Autowired
	private ResumeUploadService resumeUploadService;

	private CandidateProfile getLoggedInCandidate() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		return candidateProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));
	}

	@Override
	public ResumeResponseDto createResume(CreateResumeDto createResumeDto) {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		// Deactivate existing resumes
		List<Resume> existingResumes = resumeRepository.findByCandidateProfile(candidateProfile);

		for (Resume existingResume : existingResumes) {

			existingResume.setIsActive(false);

			resumeRepository.save(existingResume);
		}

		Resume resume = new Resume();

		resume.setCandidateProfile(candidateProfile);

		resume.setResumeTitle(createResumeDto.getResumeTitle());

		resume.setFileName(createResumeDto.getFileName());

		resume.setFilePath(createResumeDto.getFilePath());

		resume.setFileType(createResumeDto.getFileType());

		resume.setFileSizeKb(createResumeDto.getFileSizeKb());

		resume.setParsedSkills(createResumeDto.getParsedSkills());

		resume.setParsedExperience(createResumeDto.getParsedExperience());

		resume.setParsedEducation(createResumeDto.getParsedEducation());

		// New resume becomes active
		resume.setIsActive(true);

		Resume savedResume = resumeRepository.save(resume);

		return ResumeMapper.convertToDto(savedResume);
	}

	@Override
	public List<ResumeResponseDto> getMyResumes() {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		List<Resume> resumes = resumeRepository.findByCandidateProfile(candidateProfile);

		return resumes.stream().map(ResumeMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public ResumeResponseDto getResumeById(Integer resumeId) {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));

		if (!resume.getCandidateProfile().getCandidateProfileId().equals(candidateProfile.getCandidateProfileId())) {

			throw new RuntimeException("You cannot access another candidate resume");
		}

		return ResumeMapper.convertToDto(resume);
	}

	@Override
	public ResumeResponseDto updateResume(Integer resumeId, UpdateResumeDto updateResumeDto) {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));

		if (!resume.getCandidateProfile().getCandidateProfileId().equals(candidateProfile.getCandidateProfileId())) {

			throw new RuntimeException("You cannot update another candidate resume");
		}

		resume.setResumeTitle(updateResumeDto.getResumeTitle());

		resume.setFileName(updateResumeDto.getFileName());

		resume.setFilePath(updateResumeDto.getFilePath());

		resume.setFileType(updateResumeDto.getFileType());

		resume.setFileSizeKb(updateResumeDto.getFileSizeKb());

		resume.setParsedSkills(updateResumeDto.getParsedSkills());

		resume.setParsedExperience(updateResumeDto.getParsedExperience());

		resume.setParsedEducation(updateResumeDto.getParsedEducation());

		Resume updatedResume = resumeRepository.save(resume);

		/*
		 * Remove old AI cache
		 */
		jobMatchRepository.deleteAll(
		        jobMatchRepository.findByResume(updatedResume)
		);

		/*
		 * Generate fresh matches for every OPEN job
		 */
		jobMatchingService.getRecommendedJobs(updatedResume.getResumeId());

		return ResumeMapper.convertToDto(updatedResume);
	}

	@Override
	public String deleteResume(Integer resumeId) {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));

		if (!resume.getCandidateProfile().getCandidateProfileId().equals(candidateProfile.getCandidateProfileId())) {

			throw new RuntimeException("You cannot delete another candidate resume");
		}

		boolean usedInApplication = jobApplicationRepository.existsByResume(resume);

		if (usedInApplication) {

			throw new RuntimeException("Resume is already used in job applications");
		}

		resumeUploadService.deleteResumeFile(resume.getFileName());

		resumeRepository.delete(resume);

		return "Resume deleted successfully";
	}

	@Override
	public ResumeResponseDto activateResume(Integer resumeId) {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));

		if (!resume.getCandidateProfile().getCandidateProfileId().equals(candidateProfile.getCandidateProfileId())) {

			throw new RuntimeException("You cannot activate another candidate resume");
		}

		List<Resume> resumes = resumeRepository.findByCandidateProfile(candidateProfile);

		for (Resume existingResume : resumes) {

			existingResume.setIsActive(false);

			resumeRepository.save(existingResume);
		}

		resume.setIsActive(true);

		Resume updatedResume = resumeRepository.save(resume);

		return ResumeMapper.convertToDto(updatedResume);
	}

	@Override
	public ResumeResponseDto getActiveResume() {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		System.out.println("Candidate Profile ID = " + candidateProfile.getCandidateProfileId());

		List<Resume> activeResumes = resumeRepository.findByCandidateProfileAndIsActive(candidateProfile, true);

		System.out.println("Active Resume Count = " + activeResumes.size());

		if (activeResumes.isEmpty()) {

			throw new RuntimeException("No active resume found");
		}

		return ResumeMapper.convertToDto(activeResumes.get(0));
	}

	@Override
	public ResumeResponseDto replaceResumeFile(Integer resumeId, MultipartFile file) {

		CandidateProfile candidateProfile = getLoggedInCandidate();

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));

		if (!resume.getCandidateProfile().getCandidateProfileId().equals(candidateProfile.getCandidateProfileId())) {

			throw new RuntimeException("You cannot update another candidate resume");
		}

		String newFileName = resumeUploadService.replaceResumeFile(resume.getFileName(), file);

		resume.setFileName(newFileName);

		resume.setFilePath("uploads/" + newFileName);

		resume.setFileType(file.getContentType());

		resume.setFileSizeKb(java.math.BigDecimal.valueOf(file.getSize() / 1024.0));

		Resume updatedResume = resumeRepository.save(resume);

		return ResumeMapper.convertToDto(updatedResume);
	}
}