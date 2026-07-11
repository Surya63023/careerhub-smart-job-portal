package com.smartjobportal.candidate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.candidate.dto.CandidateProfileDto;
import com.smartjobportal.candidate.dto.CreateCandidateProfileDto;
import com.smartjobportal.candidate.dto.UpdateCandidateProfileDto;
import com.smartjobportal.candidate.mapper.CandidateProfileMapper;
import com.smartjobportal.candidate.service.CandidateProfileService;
import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.User;
import com.smartjobportal.repository.CandidateProfileRepository;
import com.smartjobportal.repository.UserRepository;

@Service
public class CandidateProfileServiceImpl implements CandidateProfileService {

	@Autowired
	private CandidateProfileRepository candidateProfileRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public CandidateProfileDto createProfile(CreateCandidateProfileDto request) {

		User currentUser = getCurrentUser();

		candidateProfileRepository.findByUserUserId(currentUser.getUserId()).ifPresent(profile -> {
			throw new RuntimeException("Candidate profile already exists");
		});

		CandidateProfile profile = CandidateProfileMapper.toEntity(request);

		profile.setUser(currentUser);

		CandidateProfile savedProfile = candidateProfileRepository.save(profile);

		return CandidateProfileMapper.toDto(savedProfile);
	}

	@Override
	public CandidateProfileDto getMyProfile() {

		User currentUser = getCurrentUser();

		CandidateProfile profile = candidateProfileRepository.findByUserUserId(currentUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

		return CandidateProfileMapper.toDto(profile);
	}

	@Override
	public CandidateProfileDto getProfileById(Integer candidateProfileId) {

		CandidateProfile profile = candidateProfileRepository.findById(candidateProfileId)
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

		return CandidateProfileMapper.toDto(profile);
	}

	@Override
	public CandidateProfileDto updateProfile(UpdateCandidateProfileDto request) {

		User currentUser = getCurrentUser();

		CandidateProfile profile = candidateProfileRepository.findByUserUserId(currentUser.getUserId())
				.orElseThrow(() -> new RuntimeException("Candidate profile not found"));

		CandidateProfileMapper.updateEntity(profile, request);

		CandidateProfile updatedProfile = candidateProfileRepository.save(profile);

		return CandidateProfileMapper.toDto(updatedProfile);
	}

	private User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}
}