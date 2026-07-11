package com.smartjobportal.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.company.dto.CompanyDto;
import com.smartjobportal.company.dto.CreateCompanyDto;
import com.smartjobportal.company.dto.UpdateCompanyDto;
import com.smartjobportal.company.mapper.CompanyMapper;
import com.smartjobportal.company.service.CompanyService;
import com.smartjobportal.entity.Company;
import com.smartjobportal.entity.RecruiterProfile;
import com.smartjobportal.entity.User;
import com.smartjobportal.repository.CompanyRepository;
import com.smartjobportal.repository.RecruiterProfileRepository;
import com.smartjobportal.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.smartjobportal.job.dto.CompanyFilterDto;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RecruiterProfileRepository recruiterProfileRepository;

	@Override
	public CompanyDto createCompany(CreateCompanyDto request) {

		Company company = CompanyMapper.toEntity(request);

		company.setIsVerified(false);

		Company savedCompany = companyRepository.save(company);

		return CompanyMapper.toDto(savedCompany);
	}

	@Override
	public CompanyDto getCompanyById(Integer companyId) {

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found"));

		return CompanyMapper.toDto(company);
	}

	@Override
	public CompanyDto updateCompany(Integer companyId, UpdateCompanyDto request) {

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found"));

		CompanyMapper.updateEntity(company, request);

		Company updatedCompany = companyRepository.save(company);

		return CompanyMapper.toDto(updatedCompany);
	}

	@Override
	public CompanyDto getMyCompany() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(user.getUserId())
				.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

		Company company = recruiterProfile.getCompany();

		if (company == null) {

			throw new RuntimeException("Recruiter is not assigned to a company");
		}

		return CompanyMapper.toDto(company);
	}

	/*
	 * GET ALL VERIFIED COMPANIES
	 */
	@Override
	public List<CompanyFilterDto> getVerifiedCompanies() {

		List<Company> companies = companyRepository.findAllVerifiedCompanies();

		return companies.stream().map(company -> new CompanyFilterDto(

				company.getCompanyId(),

				company.getCompanyName())).collect(Collectors.toList());
	}
}