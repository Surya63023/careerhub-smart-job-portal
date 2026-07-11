package com.smartjobportal.candidate.service;

import com.smartjobportal.candidate.dto.CandidateProfileDto;
import com.smartjobportal.candidate.dto.CreateCandidateProfileDto;
import com.smartjobportal.candidate.dto.UpdateCandidateProfileDto;

public interface CandidateProfileService {

	CandidateProfileDto createProfile(CreateCandidateProfileDto request);

	CandidateProfileDto getMyProfile();

	CandidateProfileDto getProfileById(Integer candidateProfileId);

	CandidateProfileDto updateProfile(UpdateCandidateProfileDto request);
}