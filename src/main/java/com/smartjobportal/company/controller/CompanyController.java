package com.smartjobportal.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.company.dto.CompanyDto;
import com.smartjobportal.company.dto.CreateCompanyDto;
import com.smartjobportal.company.dto.UpdateCompanyDto;
import com.smartjobportal.company.service.CompanyService;
import java.util.List;

import com.smartjobportal.job.dto.CompanyFilterDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CreateCompanyDto request) {

		return ResponseEntity.ok(companyService.createCompany(request));
	}

	@GetMapping("/{companyId}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Integer companyId) {

		return ResponseEntity.ok(companyService.getCompanyById(companyId));
	}

	// NEW API
	@GetMapping("/my-company")
	public ResponseEntity<CompanyDto> getMyCompany() {

		return ResponseEntity.ok(companyService.getMyCompany());
	}

	/*
	 * GET VERIFIED COMPANIES FOR FILTER DROPDOWN
	 */
	@GetMapping("/filter")
	public ResponseEntity<List<CompanyFilterDto>> getVerifiedCompanies() {

		return ResponseEntity.ok(

				companyService.getVerifiedCompanies());
	}

	@PutMapping("/{companyId}")
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable Integer companyId,
			@RequestBody UpdateCompanyDto request) {

		return ResponseEntity.ok(companyService.updateCompany(companyId, request));
	}
}