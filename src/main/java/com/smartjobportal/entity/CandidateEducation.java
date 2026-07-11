package com.smartjobportal.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_education")
public class CandidateEducation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "education_id")
	private Integer educationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_profile_id")
	private CandidateProfile candidateProfile;

	@Column(name = "degree")
	private String degree;

	@Column(name = "specialization")
	private String specialization;

	@Column(name = "institution_name")
	private String institutionName;

	@Column(name = "university_name")
	private String universityName;

	@Column(name = "start_year")
	private Integer startYear;

	@Column(name = "end_year")
	private Integer endYear;

	@Column(name = "percentage")
	private BigDecimal percentage;

	@Enumerated(EnumType.STRING)
	@Column(name = "current_status")
	private EducationStatus currentStatus;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {

		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}

		if (currentStatus == null) {
			currentStatus = EducationStatus.COMPLETED;
		}
	}

	public enum EducationStatus {

		PURSUING, COMPLETED
	}

	public CandidateEducation() {
	}

	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public EducationStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(EducationStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}