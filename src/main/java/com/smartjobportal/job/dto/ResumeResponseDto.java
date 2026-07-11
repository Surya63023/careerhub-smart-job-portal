package com.smartjobportal.job.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResumeResponseDto {

	private Integer resumeId;

	private String candidateName;

	private String resumeTitle;

	private String fileName;

	private String filePath;

	private String fileType;

	private BigDecimal fileSizeKb;

	private String parsedSkills;

	private String parsedExperience;

	private String parsedEducation;

	private Boolean isActive;

	private LocalDateTime uploadedAt;

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
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

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
}