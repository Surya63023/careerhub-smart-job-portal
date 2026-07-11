package com.smartjobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "job_applications")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private Integer applicationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_profile_id")
	private CandidateProfile candidateProfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	private Job job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resume_id")
	private Resume resume;

	@Enumerated(EnumType.STRING)
	@Column(name = "application_status")
	private ApplicationStatus applicationStatus;

	@Column(name = "cover_letter", columnDefinition = "TEXT")
	private String coverLetter;

	@Column(name = "applied_at")
	private LocalDateTime appliedAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public JobApplication() {

	}

	@PrePersist
	public void prePersist() {

		appliedAt = LocalDateTime.now();

		updatedAt = LocalDateTime.now();

		if (applicationStatus == null) {

			applicationStatus = ApplicationStatus.APPLIED;
		}
	}

	@PreUpdate
	public void preUpdate() {

		updatedAt = LocalDateTime.now();
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {

		this.candidateProfile = candidateProfile;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {

		this.applicationStatus = applicationStatus;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}