package com.smartjobportal.job.dto;

import java.time.LocalDateTime;

import com.smartjobportal.entity.ApplicationStatus;

public class JobApplicationResponseDto {

	private Integer applicationId;

	private Integer jobId;

	private String jobTitle;

	private String candidateName;

	private String resumeTitle;

	private ApplicationStatus applicationStatus;

	private LocalDateTime appliedAt;

	private Integer resumeId;

	private String candidateEmail;

	private String fileName;

	private String filePath;

	private LocalDateTime updatedAt;

	public JobApplicationResponseDto() {

	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {

		this.applicationId = applicationId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {

		this.jobTitle = jobTitle;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {

		this.candidateName = candidateName;
	}

	public String getResumeTitle() {
		return resumeTitle;
	}

	public void setResumeTitle(String resumeTitle) {

		this.resumeTitle = resumeTitle;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {

		this.applicationStatus = applicationStatus;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {

		this.appliedAt = appliedAt;
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}