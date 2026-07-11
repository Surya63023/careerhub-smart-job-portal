package com.smartjobportal.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.candidate.dto.CandidateProfileDto;
import com.smartjobportal.candidate.dto.CreateCandidateProfileDto;
import com.smartjobportal.candidate.dto.UpdateCandidateProfileDto;
import com.smartjobportal.candidate.service.CandidateProfileService;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/candidate/profile")
public class CandidateController {

	@Autowired
	private CandidateProfileService candidateProfileService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidateProfileDto createProfile(@Valid @RequestBody CreateCandidateProfileDto request) {

		return candidateProfileService.createProfile(request);
	}

	@GetMapping
	public CandidateProfileDto getMyProfile() {

		return candidateProfileService.getMyProfile();
	}

	@GetMapping("/{profileId}")
	public CandidateProfileDto getProfileById(@PathVariable Integer profileId) {

		return candidateProfileService.getProfileById(profileId);
	}

	@PutMapping
	public CandidateProfileDto updateProfile(@Valid @RequestBody UpdateCandidateProfileDto request) {

		return candidateProfileService.updateProfile(request);
	}

	@GetMapping("/dashboard")
	public ResponseEntity<?> getDashboard() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("================================");
		System.out.println("User = " + auth.getName());
		System.out.println("Authorities = " + auth.getAuthorities());
		System.out.println("================================");

		return ResponseEntity.ok("SUCCESS");
	}
}