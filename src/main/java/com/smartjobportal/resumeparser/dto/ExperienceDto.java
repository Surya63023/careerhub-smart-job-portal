package com.smartjobportal.resumeparser.dto;

public class ExperienceDto {

	private String companyName;

	private String designation;

	private String duration;

	public ExperienceDto() {
	}

	public ExperienceDto(String companyName, String designation, String duration) {
		this.companyName = companyName;
		this.designation = designation;
		this.duration = duration;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}