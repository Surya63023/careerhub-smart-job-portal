package com.smartjobportal.job.dto;

public class JobSearchDto {

	private String keyword;

	private String location;

	private String skill;

	private String employmentType;

	private Integer experienceRequired;
	
	private String companyName;

	private String jobStatus;

	public JobSearchDto() {

	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public Integer getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(Integer experienceRequired) {
		this.experienceRequired = experienceRequired;
	}
	
	public String getCompanyName() {
	    return companyName;
	}

	public void setCompanyName(String companyName) {
	    this.companyName = companyName;
	}

	public String getJobStatus() {
	    return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
	    this.jobStatus = jobStatus;
	}
}