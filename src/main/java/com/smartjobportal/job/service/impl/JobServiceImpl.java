package com.smartjobportal.job.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.Company;
import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.RecruiterProfile;
import com.smartjobportal.entity.User;

import com.smartjobportal.job.dto.CreateJobDto;
import com.smartjobportal.job.dto.JobResponseDto;
import com.smartjobportal.job.dto.UpdateJobDto;

import com.smartjobportal.job.mapper.JobMapper;
import com.smartjobportal.job.service.JobService;

//import com.smartjobportal.repository.CompanyRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.RecruiterProfileRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.job.dto.JobSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.smartjobportal.exception.ResourceNotFoundException;
import com.smartjobportal.exception.UnauthorizedActionException;
import com.smartjobportal.job.dto.JobPageResponseDto;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RecruiterProfileRepository recruiterProfileRepository;
//
//	@Autowired
//	private CompanyRepository companyRepository;

	/*
	 * CREATE JOB
	 */
	@Override
	public JobResponseDto createJob(CreateJobDto createJobDto) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new UnauthorizedActionException("Recruiter profile not found"));

		/*
		 * TEMPORARY COMPANY LOGIC Using company_id = 1 until Company Module is built
		 */
		Company company = recruiterProfile.getCompany();

		if (company == null) {
			throw new UnauthorizedActionException("Recruiter is not assigned to any company");
		}

		Job job = new Job();

		job.setJobTitle(createJobDto.getJobTitle());

		job.setJobDescription(createJobDto.getJobDescription());

		job.setRequiredSkills(createJobDto.getRequiredSkills());

		job.setJobLocation(createJobDto.getJobLocation());

		job.setSalaryPackage(createJobDto.getSalaryPackage());

		job.setExperienceRequired(createJobDto.getExperienceRequired());

		job.setEmploymentType(createJobDto.getEmploymentType());

		job.setApplicationDeadline(createJobDto.getApplicationDeadline());

		job.setCompany(company);

		job.setRecruiterProfile(recruiterProfile);

		Job savedJob = jobRepository.save(job);

		return JobMapper.convertToJobResponseDto(savedJob);
	}

	/*
	 * GET ALL JOBS
	 */
	@Override
	public JobPageResponseDto getAllJobs(int pageNumber, int pageSize, String sortBy, String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Job> jobPage = jobRepository.findAll(pageable);

		List<JobResponseDto> content = jobPage.getContent().stream().map(JobMapper::convertToJobResponseDto)
				.collect(Collectors.toList());

		JobPageResponseDto response = new JobPageResponseDto();

		response.setContent(content);

		response.setPageNumber(jobPage.getNumber());

		response.setPageSize(jobPage.getSize());

		response.setTotalElements(jobPage.getTotalElements());

		response.setTotalPages(jobPage.getTotalPages());

		response.setLastPage(jobPage.isLast());

		return response;
	}

	/*
	 * GET JOB BY ID
	 */
	@Override
	public JobResponseDto getJobById(Integer jobId) {

		Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

		return JobMapper.convertToJobResponseDto(job);
	}

	/*
	 * GET RECRUITER JOBS
	 */
	@Override
	public List<JobResponseDto> getRecruiterJobs() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new UnauthorizedActionException("Recruiter profile not found"));

		List<Job> jobs = jobRepository.findByRecruiterProfile(recruiterProfile);

		return jobs.stream().map(JobMapper::convertToJobResponseDto).collect(Collectors.toList());
	}

	/*
	 * UPDATE JOB
	 */
	@Override
	public JobResponseDto updateJob(Integer jobId, UpdateJobDto updateJobDto) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new UnauthorizedActionException("Recruiter profile not found"));

		Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

		/*
		 * SECURITY CHECK Recruiter can update only own jobs
		 */
		if (!job.getRecruiterProfile().getRecruiterProfileId().equals(recruiterProfile.getRecruiterProfileId())) {

			throw new UnauthorizedActionException("You are not authorized to update this job");
		}

		/*
		 * UPDATE FIELDS
		 */

		if (updateJobDto.getJobTitle() != null) {
			job.setJobTitle(updateJobDto.getJobTitle());
		}

		if (updateJobDto.getJobDescription() != null) {
			job.setJobDescription(updateJobDto.getJobDescription());
		}

		if (updateJobDto.getRequiredSkills() != null) {
			job.setRequiredSkills(updateJobDto.getRequiredSkills());
		}

		if (updateJobDto.getJobLocation() != null) {
			job.setJobLocation(updateJobDto.getJobLocation());
		}

		if (updateJobDto.getSalaryPackage() != null) {
			job.setSalaryPackage(updateJobDto.getSalaryPackage());
		}

		if (updateJobDto.getExperienceRequired() != null) {
			job.setExperienceRequired(updateJobDto.getExperienceRequired());
		}

		if (updateJobDto.getEmploymentType() != null) {
			job.setEmploymentType(updateJobDto.getEmploymentType());
		}

		if (updateJobDto.getApplicationDeadline() != null) {
			job.setApplicationDeadline(updateJobDto.getApplicationDeadline());
		}

		if (updateJobDto.getJobStatus() != null) {
			job.setJobStatus(updateJobDto.getJobStatus());
		}

		Job updatedJob = jobRepository.save(job);

		return JobMapper.convertToJobResponseDto(updatedJob);
	}

	/*
	 * DELETE JOB
	 */
	@Override
	public String deleteJob(Integer jobId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User loggedInUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(loggedInUser.getUserId())
				.orElseThrow(() -> new UnauthorizedActionException("Recruiter profile not found"));

		Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

		/*
		 * SECURITY CHECK Recruiter can delete only own jobs
		 */

		if (!job.getRecruiterProfile().getRecruiterProfileId().equals(recruiterProfile.getRecruiterProfileId())) {

			throw new UnauthorizedActionException("You are not authorized to delete this job");
		}

		jobRepository.delete(job);

		return "Job deleted successfully";
	}

	@Override
	public List<JobResponseDto> searchJobs(JobSearchDto jobSearchDto) {

		List<Job> jobs = jobRepository.searchJobs(

				jobSearchDto.getKeyword(),

				jobSearchDto.getLocation(),

				jobSearchDto.getSkill(),

				jobSearchDto.getEmploymentType(),

				jobSearchDto.getExperienceRequired());

		return jobs.stream().map(JobMapper::convertToJobResponseDto).collect(Collectors.toList());
	}
	
	@Override
	public JobPageResponseDto searchJobsWithPagination(
	        JobSearchDto jobSearchDto,
	        int pageNumber,
	        int pageSize,
	        String sortBy,
	        String sortDirection) {

	    Sort sort = sortDirection.equalsIgnoreCase("asc")
	            ? Sort.by(sortBy).ascending()
	            : Sort.by(sortBy).descending();

	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

	    Page<Job> jobPage = jobRepository.searchJobsWithPagination(

	            jobSearchDto.getKeyword(),

	            jobSearchDto.getLocation(),

	            jobSearchDto.getSkill(),

	            jobSearchDto.getEmploymentType(),

	            jobSearchDto.getExperienceRequired(),

	            jobSearchDto.getJobStatus(),

	            jobSearchDto.getCompanyName(),

	            pageable);

	    List<JobResponseDto> content = jobPage.getContent()
	            .stream()
	            .map(JobMapper::convertToJobResponseDto)
	            .collect(Collectors.toList());

	    JobPageResponseDto response = new JobPageResponseDto();

	    response.setContent(content);

	    response.setPageNumber(jobPage.getNumber());

	    response.setPageSize(jobPage.getSize());

	    response.setTotalElements(jobPage.getTotalElements());

	    response.setTotalPages(jobPage.getTotalPages());

	    response.setLastPage(jobPage.isLast());

	    return response;
	}
}