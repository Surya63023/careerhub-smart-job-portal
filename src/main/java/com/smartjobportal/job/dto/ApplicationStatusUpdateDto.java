package com.smartjobportal.job.dto;

import com.smartjobportal.entity.ApplicationStatus;

public class ApplicationStatusUpdateDto {
	private ApplicationStatus applicationStatus;

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
}