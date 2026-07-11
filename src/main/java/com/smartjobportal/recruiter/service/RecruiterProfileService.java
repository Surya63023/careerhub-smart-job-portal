package com.smartjobportal.recruiter.service;

import com.smartjobportal.recruiter.dto.CreateRecruiterProfileDto;
import com.smartjobportal.recruiter.dto.RecruiterProfileDto;
import com.smartjobportal.recruiter.dto.UpdateRecruiterProfileDto;

public interface RecruiterProfileService {

	RecruiterProfileDto createProfile(CreateRecruiterProfileDto request);

	RecruiterProfileDto getMyProfile();

	RecruiterProfileDto updateProfile(UpdateRecruiterProfileDto request);

	RecruiterProfileDto assignCompany(Integer companyId);

}