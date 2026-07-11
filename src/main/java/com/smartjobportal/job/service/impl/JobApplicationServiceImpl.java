package com.smartjobportal.job.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.JobApplication;
import com.smartjobportal.entity.RecruiterProfile;
import com.smartjobportal.entity.Resume;
import com.smartjobportal.entity.User;

import com.smartjobportal.job.dto.ApplicationStatusUpdateDto;
import com.smartjobportal.job.dto.ApplyJobDto;
import com.smartjobportal.job.dto.JobApplicationResponseDto;

import com.smartjobportal.job.mapper.JobApplicationMapper;

import com.smartjobportal.job.service.JobApplicationService;

import com.smartjobportal.repository.CandidateProfileRepository;
import com.smartjobportal.repository.JobApplicationRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.RecruiterProfileRepository;
import com.smartjobportal.repository.ResumeRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.entity.ApplicationStatusHistory;
import com.smartjobportal.repository.ApplicationStatusHistoryRepository;
import com.smartjobportal.entity.ApplicationStatus;

import com.smartjobportal.job.dto.ApplicationHistoryDto;
import com.smartjobportal.job.mapper.ApplicationHistoryMapper;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CandidateProfileRepository candidateProfileRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private RecruiterProfileRepository recruiterProfileRepository;

	@Autowired
	private ApplicationStatusHistoryRepository applicationStatusHistoryRepository;

	/*
	 * APPLY JOB
	 */
	@Override
	public JobApplicationResponseDto applyJob(ApplyJobDto applyJobDto) {

		System.out.println("===== APPLY JOB API HIT =====");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("User = " + authentication.getName());

		System.out.println("Authorities = " + authentication.getAuthorities());

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		CandidateProfile candidateProfile = candidateProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

		Job job = jobRepository.findById(applyJobDto.getJobId())
				.orElseThrow(() -> new RuntimeException("Job not found"));

		Resume resume = resumeRepository.findById(applyJobDto.getResumeId())
				.orElseThrow(() -> new RuntimeException("Resume not found"));

		/*
		 * Resume ownership validation
		 */
		if (!resume.getCandidateProfile().getCandidateProfileId().equals(candidateProfile.getCandidateProfileId())) {

			throw new RuntimeException("You cannot use another candidate resume");
		}

		/*
		 * Prevent duplicate applications
		 */
		boolean alreadyApplied = jobApplicationRepository.existsByCandidateProfileAndJob(candidateProfile, job);

		if (alreadyApplied) {

			throw new RuntimeException("You already applied for this job");
		}

		JobApplication application = new JobApplication();

		application.setCandidateProfile(candidateProfile);

		application.setJob(job);

		application.setResume(resume);

		application.setCoverLetter(applyJobDto.getCoverLetter());

		JobApplication savedApplication = jobApplicationRepository.save(application);

		/*
		 * CREATE INITIAL TIMELINE ENTRY
		 */
		ApplicationStatusHistory history = new ApplicationStatusHistory();

		history.setJobApplication(savedApplication);

		history.setOldStatus(null);

		history.setNewStatus(ApplicationStatus.APPLIED);

		history.setChangedBy(loggedInUser.getEmail());

		history.setRemarks("Application submitted");

		applicationStatusHistoryRepository.save(history);

		return JobApplicationMapper.convertToDto(savedApplication);
	}

	/*
	 * CANDIDATE APPLICATIONS
	 */
	@Override
	public List<JobApplicationResponseDto> getCandidateApplications() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		CandidateProfile candidateProfile = candidateProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

		List<JobApplication> applications = jobApplicationRepository
				.findByCandidateProfileOrderByUpdatedAtDesc(candidateProfile);

		return applications.stream().map(JobApplicationMapper::convertToDto).collect(Collectors.toList());
	}

	/*
	 * RECRUITER VIEW APPLICANTS
	 */
	@Override
	public List<JobApplicationResponseDto> getApplicantsByJob(Integer jobId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

		Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));

		/*
		 * SECURITY VALIDATION
		 *
		 * Recruiter can only view applicants for their own jobs
		 */
		if (!job.getRecruiterProfile().getRecruiterProfileId().equals(recruiterProfile.getRecruiterProfileId())) {

			throw new RuntimeException("You cannot access applicants for another recruiter's job");
		}

		List<JobApplication> applications = jobApplicationRepository.findByJob(job);

		return applications.stream().map(JobApplicationMapper::convertToDto).collect(Collectors.toList());
	}

	/*
	 * UPDATE APPLICATION STATUS
	 */
	@Override
	public JobApplicationResponseDto updateApplicationStatus(Integer applicationId, ApplicationStatusUpdateDto dto) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

		JobApplication application = jobApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Application not found"));

		/*
		 * SECURITY VALIDATION
		 */
		if (!application.getJob().getRecruiterProfile().getRecruiterProfileId()
				.equals(recruiterProfile.getRecruiterProfileId())) {

			throw new RuntimeException("You cannot update another recruiter's application");
		}

		ApplicationStatus oldStatus = application.getApplicationStatus();

		application.setApplicationStatus(dto.getApplicationStatus());

		JobApplication updatedApplication = jobApplicationRepository.save(application);

		ApplicationStatusHistory history = new ApplicationStatusHistory();

		history.setJobApplication(updatedApplication);

		history.setOldStatus(oldStatus);

		history.setNewStatus(dto.getApplicationStatus());

		history.setChangedBy(loggedInUser.getEmail());

		applicationStatusHistoryRepository.save(history);

		return JobApplicationMapper.convertToDto(updatedApplication);
	}

	@Override
	public List<ApplicationHistoryDto> getApplicationHistory(Integer applicationId) {

		JobApplication application = jobApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Application not found"));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		String role = loggedInUser.getRole();

		if ("ROLE_CANDIDATE".equals(role)) {

			CandidateProfile candidateProfile = candidateProfileRepository.findByUserUserId(loggedInUser.getUserId())
					.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

			if (!application.getCandidateProfile().getCandidateProfileId()
					.equals(candidateProfile.getCandidateProfileId())) {

				throw new RuntimeException("You cannot access another candidate application");
			}

		} else if ("ROLE_RECRUITER".equals(role)) {

			RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
					.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

			if (!application.getJob().getRecruiterProfile().getRecruiterProfileId()
					.equals(recruiterProfile.getRecruiterProfileId())) {

				throw new RuntimeException("You cannot access another recruiter's application");
			}
		}

		List<ApplicationStatusHistory> historyList = applicationStatusHistoryRepository
				.findByJobApplicationApplicationIdOrderByChangedAtDesc(applicationId);

		return historyList.stream().map(ApplicationHistoryMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<JobApplicationResponseDto> getMyApplications() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		CandidateProfile candidate = candidateProfileRepository.findByUserUserId(user.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

		List<JobApplication> applications = jobApplicationRepository
				.findByCandidateProfileOrderByUpdatedAtDesc(candidate);

		return applications.stream().map(JobApplicationMapper::convertToDto).collect(Collectors.toList());
	}
}