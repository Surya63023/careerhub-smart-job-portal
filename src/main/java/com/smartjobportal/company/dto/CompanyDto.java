package com.smartjobportal.company.dto;

import java.time.LocalDateTime;

import com.smartjobportal.entity.CompanySize;

public class CompanyDto {

	private Integer companyId;

	private String companyName;

	private String companyEmail;

	private String companyPhone;

	private String companyWebsite;

	private String companyDescription;

	private String industryType;

	private CompanySize companySize;

	private Integer foundedYear;

	private String headquartersLocation;

	private String companyLogo;

	private Boolean isVerified;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public CompanyDto() {

	}

	public CompanyDto(Integer companyId, String companyName, String companyEmail, String companyPhone,
			String companyWebsite, String companyDescription, String industryType, CompanySize companySize,
			Integer foundedYear, String headquartersLocation, String companyLogo, Boolean isVerified,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.companyPhone = companyPhone;
		this.companyWebsite = companyWebsite;
		this.companyDescription = companyDescription;
		this.industryType = industryType;
		this.companySize = companySize;
		this.foundedYear = foundedYear;
		this.headquartersLocation = headquartersLocation;
		this.companyLogo = companyLogo;
		this.isVerified = isVerified;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public String getIndustryType() {
		return industryType;
	}

	public CompanySize getCompanySize() {
		return companySize;
	}

	public Integer getFoundedYear() {
		return foundedYear;
	}

	public String getHeadquartersLocation() {
		return headquartersLocation;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public void setCompanySize(CompanySize companySize) {
		this.companySize = companySize;
	}

	public void setFoundedYear(Integer foundedYear) {
		this.foundedYear = foundedYear;
	}

	public void setHeadquartersLocation(String headquartersLocation) {
		this.headquartersLocation = headquartersLocation;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Generate Getters and Setters
}