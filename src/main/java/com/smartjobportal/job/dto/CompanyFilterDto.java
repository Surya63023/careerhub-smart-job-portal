package com.smartjobportal.job.dto;

public class CompanyFilterDto {

	private Integer companyId;

	private String companyName;

	public CompanyFilterDto() {
	}

	public CompanyFilterDto(Integer companyId, String companyName) {
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}