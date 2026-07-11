package com.smartjobportal.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidate_profiles")
public class CandidateProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_profile_id")
	private Integer candidateProfileId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "profile_headline")
	private String profileHeadline;

	@Column(name = "professional_summary", columnDefinition = "TEXT")
	private String professionalSummary;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "years_of_experience")
	private Double yearsOfExperience;

	@Column(name = "current_location")
	private String currentLocation;

	@Column(name = "linkedin_url")
	private String linkedinUrl;

	@Column(name = "github_url")
	private String githubUrl;

	@Column(name = "portfolio_url")
	private String portfolioUrl;

	@Column(name = "highest_qualification")
	private String highestQualification;

	@Column(name = "current_job_title")
	private String currentJobTitle;

	@Column(name = "expected_salary")
	private BigDecimal expectedSalary;

	@Column(name = "profile_picture")
	private String profilePicture;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public CandidateProfile() {

	}

	@PrePersist
	public void prePersist() {

		createdAt = LocalDateTime.now();

		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {

		updatedAt = LocalDateTime.now();
	}

	public Integer getCandidateProfileId() {
		return candidateProfileId;
	}

	public void setCandidateProfileId(Integer candidateProfileId) {

		this.candidateProfileId = candidateProfileId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProfileHeadline() {
		return profileHeadline;
	}

	public void setProfileHeadline(String profileHeadline) {

		this.profileHeadline = profileHeadline;
	}

	public String getProfessionalSummary() {
		return professionalSummary;
	}

	public void setProfessionalSummary(String professionalSummary) {

		this.professionalSummary = professionalSummary;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {

		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Double yearsOfExperience) {

		this.yearsOfExperience = yearsOfExperience;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {

		this.currentLocation = currentLocation;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {

		this.linkedinUrl = linkedinUrl;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {

		this.githubUrl = githubUrl;
	}

	public String getPortfolioUrl() {
		return portfolioUrl;
	}

	public void setPortfolioUrl(String portfolioUrl) {

		this.portfolioUrl = portfolioUrl;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {

		this.highestQualification = highestQualification;
	}

	public String getCurrentJobTitle() {
		return currentJobTitle;
	}

	public void setCurrentJobTitle(String currentJobTitle) {

		this.currentJobTitle = currentJobTitle;
	}

	public BigDecimal getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(BigDecimal expectedSalary) {

		this.expectedSalary = expectedSalary;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {

		this.profilePicture = profilePicture;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}