package com.smartjobportal.job.dto;

import java.time.LocalDateTime;

public class JobResponseDto {

	private Integer jobId;

	private String jobTitle;

	private String jobDescription;

	private String requiredSkills;

	private String jobLocation;

	private String salaryPackage;

	private Integer experienceRequired;

	private String employmentType;

	private String jobStatus;

	private LocalDateTime applicationDeadline;

	private LocalDateTime createdAt;

	private String recruiterName;
	
	private String companyName;

	public JobResponseDto() {

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

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getSalaryPackage() {
		return salaryPackage;
	}

	public void setSalaryPackage(String salaryPackage) {
		this.salaryPackage = salaryPackage;
	}

	public Integer getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(Integer experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public LocalDateTime getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(LocalDateTime applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	
	public String getCompanyName() {
	    return companyName;
	}
	
	public void setCompanyName(String companyName) {
	    this.companyName = companyName;
	}
}