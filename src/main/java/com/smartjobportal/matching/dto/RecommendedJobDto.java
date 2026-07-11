package com.smartjobportal.matching.dto;

public class RecommendedJobDto {

	private Integer jobId;

	private String jobTitle;

	private String companyName;

	private Double matchPercentage;

	private Boolean recommended;

	public RecommendedJobDto() {
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getMatchPercentage() {
		return matchPercentage;
	}

	public void setMatchPercentage(Double matchPercentage) {
		this.matchPercentage = matchPercentage;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}
}