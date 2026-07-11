package com.smartjobportal.entity;

import java.math.BigDecimal;
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
@Table(name = "resumes")
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resume_id")
	private Integer resumeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_profile_id")
	private CandidateProfile candidateProfile;

	@Column(name = "resume_title")
	private String resumeTitle;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_size_kb")
	private BigDecimal fileSizeKb;

	@Column(name = "parsed_skills", columnDefinition = "TEXT")
	private String parsedSkills;

	@Column(name = "parsed_experience", columnDefinition = "TEXT")
	private String parsedExperience;

	@Column(name = "parsed_education", columnDefinition = "TEXT")
	private String parsedEducation;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "uploaded_at")
	private LocalDateTime uploadedAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Resume() {

	}

	@PrePersist
	public void prePersist() {

		uploadedAt = LocalDateTime.now();

		updatedAt = LocalDateTime.now();

		if (isActive == null) {

			isActive = true;
		}
	}

	@PreUpdate
	public void preUpdate() {

		updatedAt = LocalDateTime.now();
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {

		this.candidateProfile = candidateProfile;
	}

	public String getResumeTitle() {
		return resumeTitle;
	}

	public void setResumeTitle(String resumeTitle) {

		this.resumeTitle = resumeTitle;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {

		this.fileType = fileType;
	}

	public BigDecimal getFileSizeKb() {
		return fileSizeKb;
	}

	public void setFileSizeKb(BigDecimal fileSizeKb) {

		this.fileSizeKb = fileSizeKb;
	}

	public String getParsedSkills() {
		return parsedSkills;
	}

	public void setParsedSkills(String parsedSkills) {

		this.parsedSkills = parsedSkills;
	}

	public String getParsedExperience() {
		return parsedExperience;
	}

	public void setParsedExperience(String parsedExperience) {

		this.parsedExperience = parsedExperience;
	}

	public String getParsedEducation() {
		return parsedEducation;
	}

	public void setParsedEducation(String parsedEducation) {

		this.parsedEducation = parsedEducation;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {

		this.isActive = isActive;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}