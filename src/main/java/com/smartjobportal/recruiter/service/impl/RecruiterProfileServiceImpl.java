package com.smartjobportal.recruiter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.RecruiterProfile;
import com.smartjobportal.entity.User;
import com.smartjobportal.recruiter.dto.CreateRecruiterProfileDto;
import com.smartjobportal.recruiter.dto.RecruiterProfileDto;
import com.smartjobportal.recruiter.dto.UpdateRecruiterProfileDto;
import com.smartjobportal.recruiter.mapper.RecruiterProfileMapper;
import com.smartjobportal.recruiter.service.RecruiterProfileService;
import com.smartjobportal.repository.RecruiterProfileRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.entity.Company;
import com.smartjobportal.repository.CompanyRepository;

@Service
public class RecruiterProfileServiceImpl implements RecruiterProfileService {

	@Autowired
	private RecruiterProfileRepository recruiterProfileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyRepository companyRepository;

	

	@Override
	public RecruiterProfileDto createProfile(CreateRecruiterProfileDto request) {

		User currentUser = getCurrentUser();

		recruiterProfileRepository.findByUserUserId(currentUser.getUserId()).ifPresent(profile -> {

			throw new RuntimeException("Recruiter profile already exists");
		});

		RecruiterProfile recruiterProfile = RecruiterProfileMapper.toEntity(request);

		recruiterProfile.setUser(currentUser);

		recruiterProfile.setIsVerified(false);

		RecruiterProfile savedProfile = recruiterProfileRepository.save(recruiterProfile);

		return RecruiterProfileMapper.toDto(savedProfile);
	}

	@Override
	public RecruiterProfileDto getMyProfile() {

		User currentUser = getCurrentUser();

		RecruiterProfile profile = recruiterProfileRepository.findByUserUserId(currentUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

		return RecruiterProfileMapper.toDto(profile);
	}

	@Override
	public RecruiterProfileDto updateProfile(UpdateRecruiterProfileDto request) {

		User currentUser = getCurrentUser();

		RecruiterProfile profile = recruiterProfileRepository.findByUserUserId(currentUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

		RecruiterProfileMapper.updateEntity(profile, request);

		RecruiterProfile updatedProfile = recruiterProfileRepository.save(profile);

		return RecruiterProfileMapper.toDto(updatedProfile);
	}

	private User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public RecruiterProfileDto assignCompany(Integer companyId) {

		User currentUser = getCurrentUser();

		RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserUserId(currentUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found"));

		recruiterProfile.setCompany(company);

		RecruiterProfile savedProfile = recruiterProfileRepository.save(recruiterProfile);

		return RecruiterProfileMapper.toDto(savedProfile);
	}
}