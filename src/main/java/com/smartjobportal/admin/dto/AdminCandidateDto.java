package com.smartjobportal.admin.dto;

public class AdminCandidateDto {

	private Integer candidateProfileId;

	private Integer userId;

	private String candidateName;

	private String email;

	private String currentJobTitle;

	private String currentLocation;

	private String accountStatus;

	public AdminCandidateDto() {

	}

	public Integer getCandidateProfileId() {
		return candidateProfileId;
	}

	public void setCandidateProfileId(Integer candidateProfileId) {
		this.candidateProfileId = candidateProfileId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentJobTitle() {
		return currentJobTitle;
	}

	public void setCurrentJobTitle(String currentJobTitle) {
		this.currentJobTitle = currentJobTitle;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
}