package com.smartjobportal.dto;

import java.math.BigDecimal;

public class CreateRecruiterProfileRequest {

	private String companyName;

	private String designation;

	private String department;

	private BigDecimal yearsOfExperience;

	private String workLocation;

	private String linkedinUrl;

	private String recruiterBio;

	public CreateRecruiterProfileRequest() {

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(BigDecimal yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	public String getRecruiterBio() {
		return recruiterBio;
	}

	public void setRecruiterBio(String recruiterBio) {
		this.recruiterBio = recruiterBio;
	}
}