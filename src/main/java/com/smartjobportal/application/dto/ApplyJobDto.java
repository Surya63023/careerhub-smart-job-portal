package com.smartjobportal.application.dto;

public class ApplyJobDto {

	private Integer resumeId;

	private String coverLetter;

	public ApplyJobDto() {

	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
}