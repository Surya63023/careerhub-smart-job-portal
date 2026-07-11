package com.smartjobportal.job.dto;

import java.math.BigDecimal;

public class UpdateResumeDto {

	private String resumeTitle;

	private String fileName;

	private String filePath;

	private String fileType;

	private BigDecimal fileSizeKb;

	private String parsedSkills;

	private String parsedExperience;

	private String parsedEducation;

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
}