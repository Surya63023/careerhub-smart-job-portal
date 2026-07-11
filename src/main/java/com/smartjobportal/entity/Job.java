package com.smartjobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Integer jobId;

	@Column(name = "job_title", nullable = false)
	private String jobTitle;

	@Column(name = "job_description", columnDefinition = "TEXT")
	private String jobDescription;

	@Column(name = "required_skills")
	private String requiredSkills;

	@Column(name = "job_location")
	private String jobLocation;

	@Column(name = "salary_package")
	private String salaryPackage;

	@Column(name = "experience_required")
	private Integer experienceRequired;

	@Column(name = "employment_type")
	private String employmentType;

	@Column(name = "job_status")
	private String jobStatus;

	@Column(name = "application_deadline")
	private LocalDateTime applicationDeadline;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	/*
	 * Recruiter who created this job
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recruiter_profile_id")
	private RecruiterProfile recruiterProfile;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public RecruiterProfile getRecruiterProfile() {
		return recruiterProfile;
	}

	public void setRecruiterProfile(RecruiterProfile recruiterProfile) {
		this.recruiterProfile = recruiterProfile;
	}

	public Job() {

	}

	@PrePersist
	public void prePersist() {

		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();

		if (jobStatus == null) {
			jobStatus = "OPEN";
		}
	}

	@PreUpdate
	public void preUpdate() {

		updatedAt = LocalDateTime.now();
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	

}