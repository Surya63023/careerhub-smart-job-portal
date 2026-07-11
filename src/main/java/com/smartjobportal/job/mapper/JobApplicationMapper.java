package com.smartjobportal.job.mapper;

import com.smartjobportal.entity.JobApplication;
import com.smartjobportal.job.dto.JobApplicationResponseDto;

public class JobApplicationMapper {

	public static JobApplicationResponseDto convertToDto(JobApplication application) {

		JobApplicationResponseDto dto = new JobApplicationResponseDto();

		dto.setApplicationId(application.getApplicationId());

		dto.setJobId(application.getJob().getJobId());

		dto.setJobTitle(application.getJob().getJobTitle());

		String candidateName = "Unknown Candidate";

		if (application.getCandidateProfile() != null && application.getCandidateProfile().getUser() != null) {

			String firstName = application.getCandidateProfile().getUser().getFirstName();

			String lastName = application.getCandidateProfile().getUser().getLastName();

			candidateName = ((firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName)).trim();

			if (candidateName.isBlank()) {

				candidateName = "Unknown Candidate";
			}
		}

		dto.setCandidateName(candidateName);

		if (application.getCandidateProfile() != null && application.getCandidateProfile().getUser() != null) {

			dto.setCandidateEmail(

					application.getCandidateProfile().getUser().getEmail());
		}

		dto.setResumeId(application.getResume().getResumeId());

		dto.setResumeTitle(application.getResume().getResumeTitle());

		dto.setFileName(application.getResume().getFileName());

		dto.setFilePath(application.getResume().getFilePath());

		dto.setApplicationStatus(application.getApplicationStatus());

		dto.setAppliedAt(application.getAppliedAt());
		
		dto.setUpdatedAt(application.getUpdatedAt());
		
		return dto;
	}
}