package com.smartjobportal.job.mapper;

import com.smartjobportal.entity.Job;
import com.smartjobportal.job.dto.JobResponseDto;

public class JobMapper {

	public static JobResponseDto convertToJobResponseDto(Job job) {

		JobResponseDto dto = new JobResponseDto();

		dto.setJobId(job.getJobId());

		dto.setJobTitle(job.getJobTitle());

		dto.setJobDescription(job.getJobDescription());

		dto.setRequiredSkills(job.getRequiredSkills());

		dto.setJobLocation(job.getJobLocation());

		dto.setSalaryPackage(job.getSalaryPackage());

		dto.setExperienceRequired(job.getExperienceRequired());

		dto.setEmploymentType(job.getEmploymentType());

		dto.setJobStatus(job.getJobStatus());

		dto.setApplicationDeadline(job.getApplicationDeadline());

		dto.setCreatedAt(job.getCreatedAt());

		if (job.getRecruiterProfile() != null && job.getRecruiterProfile().getUser() != null) {

			dto.setRecruiterName(job.getRecruiterProfile().getUser().getFullName());
		}
		if (job.getCompany() != null) {

			dto.setCompanyName(job.getCompany().getCompanyName());
		}
		return dto;
	}
}