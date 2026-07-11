package com.smartjobportal.job.service;

import java.util.List;

import com.smartjobportal.job.dto.CreateJobDto;
import com.smartjobportal.job.dto.JobResponseDto;
import com.smartjobportal.job.dto.UpdateJobDto;
import com.smartjobportal.job.dto.JobSearchDto;
import com.smartjobportal.job.dto.JobPageResponseDto;

public interface JobService {

	JobResponseDto createJob(CreateJobDto createJobDto);

	JobPageResponseDto getAllJobs(int pageNumber, int pageSize, String sortBy, String sortDirection);

	JobResponseDto getJobById(Integer jobId);

	List<JobResponseDto> getRecruiterJobs();

	JobResponseDto updateJob(Integer jobId, UpdateJobDto updateJobDto);

	String deleteJob(Integer jobId);

	List<JobResponseDto> searchJobs(JobSearchDto jobSearchDto);

	JobPageResponseDto searchJobsWithPagination(JobSearchDto jobSearchDto, int pageNumber, int pageSize, String sortBy,
			String sortDirection);

}