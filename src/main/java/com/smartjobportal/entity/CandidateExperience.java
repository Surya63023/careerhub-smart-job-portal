package com.smartjobportal.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_experience")
public class CandidateExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_id")
	private Integer experienceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_profile_id")
	private CandidateProfile candidateProfile;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "job_title")
	private String jobTitle;

	@Enumerated(EnumType.STRING)
	@Column(name = "employment_type")
	private EmploymentType employmentType;

	@Column(name = "job_location")
	private String jobLocation;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "currently_working")
	private Boolean currentlyWorking;

	@Column(name = "job_description", columnDefinition = "TEXT")
	private String jobDescription;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {

		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}

		if (employmentType == null) {
			employmentType = EmploymentType.FULL_TIME;
		}

		if (currentlyWorking == null) {
			currentlyWorking = false;
		}
	}

	public enum EmploymentType {

		FULL_TIME, PART_TIME, INTERNSHIP, FREELANCE, CONTRACT
	}

	public CandidateExperience() {
	}

	public Integer getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Integer experienceId) {
		this.experienceId = experienceId;
	}

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Boolean getCurrentlyWorking() {
		return currentlyWorking;
	}

	public void setCurrentlyWorking(Boolean currentlyWorking) {
		this.currentlyWorking = currentlyWorking;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}