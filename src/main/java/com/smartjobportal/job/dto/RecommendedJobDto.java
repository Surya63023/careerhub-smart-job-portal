package com.smartjobportal.job.dto;

import java.math.BigDecimal;

public class RecommendedJobDto {

	private Integer jobId;

	private String jobTitle;

	private String companyName;

	private BigDecimal matchPercentage;

	private String matchedSkills;

	private String missingSkills;

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

	public BigDecimal getMatchPercentage() {
		return matchPercentage;
	}

	public void setMatchPercentage(BigDecimal matchPercentage) {
		this.matchPercentage = matchPercentage;
	}

	public String getMatchedSkills() {
		return matchedSkills;
	}

	public void setMatchedSkills(String matchedSkills) {
		this.matchedSkills = matchedSkills;
	}

	public String getMissingSkills() {
		return missingSkills;
	}

	public void setMissingSkills(String missingSkills) {
		this.missingSkills = missingSkills;
	}
}