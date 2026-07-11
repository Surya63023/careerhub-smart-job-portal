package com.smartjobportal.admin.dto;

public class AdminRecruiterDto {

	private Integer recruiterProfileId;

	private Integer userId;

	private String recruiterName;

	private String email;

	private String companyName;

	private String designation;

	private String workLocation;

	private Boolean verified;

	private String accountStatus;

	public AdminRecruiterDto() {

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

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
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

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
}