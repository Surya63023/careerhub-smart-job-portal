package com.smartjobportal.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.smartjobportal.admin.dto.AdminDashboardStatsDto;
import com.smartjobportal.admin.dto.AdminJobDto;
import com.smartjobportal.admin.service.AdminService;
import com.smartjobportal.repository.CandidateProfileRepository;
import com.smartjobportal.repository.CompanyRepository;
import com.smartjobportal.repository.JobApplicationRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.RecruiterProfileRepository;
import com.smartjobportal.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import com.smartjobportal.admin.dto.UpdateUserStatusDto;
import com.smartjobportal.admin.dto.AdminUserDto;
import com.smartjobportal.entity.User;
import com.smartjobportal.admin.dto.AdminApplicationDto;
import com.smartjobportal.admin.dto.AdminCandidateDto;
import com.smartjobportal.admin.dto.AdminCompanyDto;
import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.Company;
import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.JobApplication;
import com.smartjobportal.admin.dto.AdminRecruiterDto;
import com.smartjobportal.entity.RecruiterProfile;
import com.smartjobportal.repository.AdminLogRepository;
import com.smartjobportal.admin.dto.AdminLogDto;
import com.smartjobportal.entity.AdminLog;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CandidateProfileRepository candidateProfileRepository;

	@Autowired
	private RecruiterProfileRepository recruiterProfileRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Autowired
	private AdminLogRepository adminLogRepository;

	@Override
	public List<AdminUserDto> getAllUsers() {

		return userRepository.findAll().stream().map(this::mapToUserDto).collect(Collectors.toList());
	}

	@Override
	public AdminDashboardStatsDto getDashboardStats() {

		AdminDashboardStatsDto dto = new AdminDashboardStatsDto();

		dto.setTotalUsers(userRepository.count());

		dto.setTotalCandidates(candidateProfileRepository.count());

		dto.setTotalRecruiters(recruiterProfileRepository.count());

		dto.setTotalCompanies(companyRepository.count());

		dto.setTotalJobs(jobRepository.count());

		dto.setTotalApplications(jobApplicationRepository.count());

		return dto;
	}

	private AdminUserDto mapToUserDto(User user) {

		AdminUserDto dto = new AdminUserDto();

		dto.setUserId(user.getUserId());

		dto.setFirstName(user.getFirstName());

		dto.setLastName(user.getLastName());

		dto.setEmail(user.getEmail());

		dto.setRole(user.getRole());

		dto.setAccountStatus(user.getAccountStatus() != null ? user.getAccountStatus().name() : "ACTIVE");

		return dto;
	}

	private AdminCandidateDto mapToCandidateDto(CandidateProfile candidate) {

		AdminCandidateDto dto = new AdminCandidateDto();

		dto.setCandidateProfileId(candidate.getCandidateProfileId());

		dto.setUserId(candidate.getUser().getUserId());

		String firstName = candidate.getUser().getFirstName() != null ? candidate.getUser().getFirstName() : "";

		String lastName = candidate.getUser().getLastName() != null ? candidate.getUser().getLastName() : "";

		dto.setCandidateName((firstName + " " + lastName).trim());

		dto.setEmail(candidate.getUser().getEmail());

		dto.setCurrentJobTitle(candidate.getCurrentJobTitle());

		dto.setCurrentLocation(candidate.getCurrentLocation());

		dto.setAccountStatus(candidate.getUser().getAccountStatus().name());

		return dto;
	}

	private AdminRecruiterDto mapToRecruiterDto(RecruiterProfile recruiter) {

		AdminRecruiterDto dto = new AdminRecruiterDto();

		dto.setRecruiterProfileId(recruiter.getRecruiterProfileId());

		dto.setUserId(recruiter.getUser().getUserId());

		String firstName = recruiter.getUser().getFirstName() != null ? recruiter.getUser().getFirstName() : "";

		String lastName = recruiter.getUser().getLastName() != null ? recruiter.getUser().getLastName() : "";

		dto.setRecruiterName((firstName + " " + lastName).trim());

		dto.setEmail(recruiter.getUser().getEmail());

		dto.setCompanyName(recruiter.getCompanyName());

		dto.setDesignation(recruiter.getDesignation());

		dto.setWorkLocation(recruiter.getWorkLocation());

		dto.setVerified(recruiter.getIsVerified());

		dto.setAccountStatus(recruiter.getUser().getAccountStatus().name());

		return dto;
	}

	@Override
	public AdminUserDto updateUserStatus(Integer userId, UpdateUserStatusDto dto) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		user.setAccountStatus(dto.getAccountStatus());

		User updatedUser = userRepository.save(user);

		return mapToUserDto(updatedUser);
	}

	@Override
	public List<AdminCandidateDto> getAllCandidates() {

		return candidateProfileRepository.findAll().stream().map(this::mapToCandidateDto).toList();
	}

	@Override
	public List<AdminRecruiterDto> getAllRecruiters() {

		return recruiterProfileRepository.findAll().stream().map(this::mapToRecruiterDto).toList();
	}

	@Override
	public List<AdminCompanyDto> getAllCompanies() {

		return companyRepository.findAll().stream().map(this::mapToCompanyDto).collect(Collectors.toList());
	}

	private AdminCompanyDto mapToCompanyDto(Company company) {

		AdminCompanyDto dto = new AdminCompanyDto();

		dto.setCompanyId(company.getCompanyId());

		dto.setCompanyName(company.getCompanyName());

		dto.setIndustryType(company.getIndustryType());

		dto.setCompanyWebsite(company.getCompanyWebsite());

		dto.setCompanyLocation(company.getHeadquartersLocation());

		dto.setCompanySize(

				company.getCompanySize() != null ? company.getCompanySize().name() : "-");

		return dto;
	}

	@Override
	public List<AdminJobDto> getAllJobs() {

		return jobRepository.findAll().stream().map(this::mapToJobDto).collect(Collectors.toList());
	}

	private AdminJobDto mapToJobDto(Job job) {

		AdminJobDto dto = new AdminJobDto();

		dto.setJobId(job.getJobId());

		dto.setJobTitle(job.getJobTitle());

		dto.setCompanyName(job.getCompany() != null ? job.getCompany().getCompanyName() : "-");

		dto.setJobLocation(job.getJobLocation());

		dto.setEmploymentType(job.getEmploymentType());

		dto.setJobStatus(job.getJobStatus());

		return dto;
	}

	@Override
	public List<AdminApplicationDto> getAllApplications() {

		List<JobApplication> applications = jobApplicationRepository.findAll();

		return applications.stream().map(this::mapToApplicationDto).toList();
	}

	private AdminApplicationDto mapToApplicationDto(JobApplication application) {

		AdminApplicationDto dto = new AdminApplicationDto();

		dto.setApplicationId(application.getApplicationId());

		dto.setApplicationStatus(application.getApplicationStatus().name());

		if (application.getJob() != null) {

			dto.setJobTitle(application.getJob().getJobTitle());

			if (application.getJob().getCompany() != null) {

				dto.setCompanyName(application.getJob().getCompany().getCompanyName());
			}
		}

		if (application.getCandidateProfile() != null && application.getCandidateProfile().getUser() != null) {

			dto.setCandidateName(application.getCandidateProfile().getUser().getFullName());
		}

		return dto;
	}

	private AdminLogDto mapToLogDto(AdminLog log) {

		AdminLogDto dto = new AdminLogDto();

		dto.setLogId(log.getAdminLogId());

		dto.setActionType(log.getActionType().name());

		dto.setModuleName(log.getModuleName());

		dto.setDescription(log.getActionDescription());

		dto.setIpAddress(log.getIpAddress());

		dto.setCreatedAt(log.getCreatedAt());

		if (log.getAdminUser() != null) {

			User adminUser = log.getAdminUser();

			String firstName = adminUser.getFirstName() != null ? adminUser.getFirstName() : "";

			String lastName = adminUser.getLastName() != null ? adminUser.getLastName() : "";

			dto.setAdminName((firstName + " " + lastName).trim());
		}

		return dto;
	}

	@Override
	public List<AdminLogDto> getAllLogs() {

		return adminLogRepository.findAll().stream().map(this::mapToLogDto).toList();
	}

}