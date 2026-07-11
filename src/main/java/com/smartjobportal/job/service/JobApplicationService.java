package com.smartjobportal.job.service;

import java.util.List;

import com.smartjobportal.job.dto.ApplicationStatusUpdateDto;
import com.smartjobportal.job.dto.ApplyJobDto;
import com.smartjobportal.job.dto.JobApplicationResponseDto;
import com.smartjobportal.job.dto.ApplicationHistoryDto;

public interface JobApplicationService {

	JobApplicationResponseDto applyJob(ApplyJobDto applyJobDto);

	List<JobApplicationResponseDto> getApplicantsByJob(Integer jobId);

	JobApplicationResponseDto updateApplicationStatus(Integer applicationId, ApplicationStatusUpdateDto dto);

	List<JobApplicationResponseDto> getCandidateApplications();

	List<ApplicationHistoryDto> getApplicationHistory(Integer applicationId);

	List<JobApplicationResponseDto> getMyApplications();
}