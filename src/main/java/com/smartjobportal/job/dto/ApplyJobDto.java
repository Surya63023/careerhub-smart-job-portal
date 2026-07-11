package com.smartjobportal.job.dto;

public class ApplyJobDto {

	private Integer jobId;

	private Integer resumeId;

	private String coverLetter;

	public ApplyJobDto() {

	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
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