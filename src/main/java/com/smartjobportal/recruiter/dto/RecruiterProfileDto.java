package com.smartjobportal.recruiter.dto;

import java.math.BigDecimal;

public class RecruiterProfileDto {

	private Integer recruiterProfileId;

	private Integer userId;

	private String fullName;

	private String email;

	private String companyName;

	private String designation;

	private String department;

	private BigDecimal yearsOfExperience;

	private String workLocation;

	private String linkedinUrl;

	private String recruiterBio;

	private Boolean isVerified;

	public RecruiterProfileDto() {

	}

	public Integer getRecruiterProfileId() {
		return recruiterProfileId;
	}

	public void setRecruiterProfileId(Integer recruiterProfileId) {
		this.recruiterProfileId = recruiterProfileId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
}