package com.smartjobportal.recruiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.recruiter.dto.CreateRecruiterProfileDto;
import com.smartjobportal.recruiter.dto.RecruiterProfileDto;
import com.smartjobportal.recruiter.dto.UpdateRecruiterProfileDto;
import com.smartjobportal.recruiter.service.RecruiterProfileService;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

	@Autowired
	private RecruiterProfileService recruiterProfileService;

	@PostMapping("/profile")
	public ResponseEntity<RecruiterProfileDto> createProfile(@RequestBody CreateRecruiterProfileDto request) {

		return ResponseEntity.ok(recruiterProfileService.createProfile(request));
	}

	@GetMapping("/profile")
	public ResponseEntity<RecruiterProfileDto> getMyProfile() {

		return ResponseEntity.ok(recruiterProfileService.getMyProfile());
	}

	@PutMapping("/profile")
	public ResponseEntity<RecruiterProfileDto> updateProfile(@RequestBody UpdateRecruiterProfileDto request) {

		return ResponseEntity.ok(recruiterProfileService.updateProfile(request));
	}

	@PutMapping("/company/{companyId}")
	public ResponseEntity<RecruiterProfileDto> assignCompany(@PathVariable Integer companyId) {

		return ResponseEntity.ok(recruiterProfileService.assignCompany(companyId));
	}
}