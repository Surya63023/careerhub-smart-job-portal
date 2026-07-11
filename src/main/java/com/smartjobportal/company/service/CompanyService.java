package com.smartjobportal.company.service;

import com.smartjobportal.company.dto.CompanyDto;
import com.smartjobportal.company.dto.CreateCompanyDto;
import com.smartjobportal.company.dto.UpdateCompanyDto;
import java.util.List;
import com.smartjobportal.job.dto.CompanyFilterDto;

public interface CompanyService {

	CompanyDto createCompany(CreateCompanyDto request);

	CompanyDto getCompanyById(Integer companyId);

	CompanyDto updateCompany(Integer companyId, UpdateCompanyDto request);

	// NEW
	CompanyDto getMyCompany();

	/*
	 * GET ALL VERIFIED COMPANIES FOR FILTER DROPDOWN
	 */
	List<CompanyFilterDto> getVerifiedCompanies();
	
	
}