package com.smartjobportal.company.mapper;

import com.smartjobportal.company.dto.CompanyDto;
import com.smartjobportal.company.dto.CreateCompanyDto;
import com.smartjobportal.company.dto.UpdateCompanyDto;
import com.smartjobportal.entity.Company;

public class CompanyMapper {

	private CompanyMapper() {

	}

	public static CompanyDto toDto(Company company) {

		if (company == null) {
			return null;
		}

		CompanyDto dto = new CompanyDto();

		dto.setCompanyId(company.getCompanyId());
		dto.setCompanyName(company.getCompanyName());
		dto.setCompanyEmail(company.getCompanyEmail());
		dto.setCompanyPhone(company.getCompanyPhone());
		dto.setCompanyWebsite(company.getCompanyWebsite());
		dto.setCompanyDescription(company.getCompanyDescription());
		dto.setIndustryType(company.getIndustryType());
		dto.setCompanySize(company.getCompanySize());
		dto.setFoundedYear(company.getFoundedYear());
		dto.setHeadquartersLocation(company.getHeadquartersLocation());
		dto.setCompanyLogo(company.getCompanyLogo());
		dto.setIsVerified(company.getIsVerified());
		dto.setCreatedAt(company.getCreatedAt());
		dto.setUpdatedAt(company.getUpdatedAt());

		return dto;
	}

	public static Company toEntity(CreateCompanyDto dto) {

		if (dto == null) {
			return null;
		}

		Company company = new Company();

		company.setCompanyName(dto.getCompanyName());
		company.setCompanyEmail(dto.getCompanyEmail());
		company.setCompanyPhone(dto.getCompanyPhone());
		company.setCompanyWebsite(dto.getCompanyWebsite());
		company.setCompanyDescription(dto.getCompanyDescription());
		company.setIndustryType(dto.getIndustryType());
		company.setCompanySize(dto.getCompanySize());
		company.setFoundedYear(dto.getFoundedYear());
		company.setHeadquartersLocation(dto.getHeadquartersLocation());
		company.setCompanyLogo(dto.getCompanyLogo());

		return company;
	}

	public static void updateEntity(Company company, UpdateCompanyDto dto) {

		if (dto.getCompanyName() != null) {
			company.setCompanyName(dto.getCompanyName());
		}

		if (dto.getCompanyEmail() != null) {
			company.setCompanyEmail(dto.getCompanyEmail());
		}

		if (dto.getCompanyPhone() != null) {
			company.setCompanyPhone(dto.getCompanyPhone());
		}

		if (dto.getCompanyWebsite() != null) {
			company.setCompanyWebsite(dto.getCompanyWebsite());
		}

		if (dto.getCompanyDescription() != null) {
			company.setCompanyDescription(dto.getCompanyDescription());
		}

		if (dto.getIndustryType() != null) {
			company.setIndustryType(dto.getIndustryType());
		}

		if (dto.getCompanySize() != null) {
			company.setCompanySize(dto.getCompanySize());
		}

		if (dto.getFoundedYear() != null) {
			company.setFoundedYear(dto.getFoundedYear());
		}

		if (dto.getHeadquartersLocation() != null) {
			company.setHeadquartersLocation(dto.getHeadquartersLocation());
		}

		if (dto.getCompanyLogo() != null) {
			company.setCompanyLogo(dto.getCompanyLogo());
		}
	}
}