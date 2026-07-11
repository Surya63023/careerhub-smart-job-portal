package com.smartjobportal.company.dto;

import com.smartjobportal.entity.CompanySize;

public class UpdateCompanyDto {

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

	public UpdateCompanyDto() {

	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public CompanySize getCompanySize() {
		return companySize;
	}

	public void setCompanySize(CompanySize companySize) {
		this.companySize = companySize;
	}

	public Integer getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(Integer foundedYear) {
		this.foundedYear = foundedYear;
	}

	public String getHeadquartersLocation() {
		return headquartersLocation;
	}

	public void setHeadquartersLocation(String headquartersLocation) {
		this.headquartersLocation = headquartersLocation;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
}