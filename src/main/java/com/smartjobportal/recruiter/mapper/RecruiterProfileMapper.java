package com.smartjobportal.recruiter.mapper;

import com.smartjobportal.entity.RecruiterProfile;
import com.smartjobportal.recruiter.dto.CreateRecruiterProfileDto;
import com.smartjobportal.recruiter.dto.RecruiterProfileDto;
import com.smartjobportal.recruiter.dto.UpdateRecruiterProfileDto;

public class RecruiterProfileMapper {

	private RecruiterProfileMapper() {

	}

	public static RecruiterProfileDto toDto(RecruiterProfile recruiterProfile) {

		if (recruiterProfile == null) {
			return null;
		}

		RecruiterProfileDto dto = new RecruiterProfileDto();

		dto.setRecruiterProfileId(recruiterProfile.getRecruiterProfileId());

		if (recruiterProfile.getUser() != null) {

			dto.setUserId(recruiterProfile.getUser().getUserId());

			dto.setFullName(recruiterProfile.getUser().getFullName());

			dto.setEmail(recruiterProfile.getUser().getEmail());
		}

		dto.setCompanyName(recruiterProfile.getCompanyName());

		dto.setDesignation(recruiterProfile.getDesignation());

		dto.setDepartment(recruiterProfile.getDepartment());

		dto.setYearsOfExperience(recruiterProfile.getYearsOfExperience());

		dto.setWorkLocation(recruiterProfile.getWorkLocation());

		dto.setLinkedinUrl(recruiterProfile.getLinkedinUrl());

		dto.setRecruiterBio(recruiterProfile.getRecruiterBio());

		dto.setIsVerified(recruiterProfile.getIsVerified());

		return dto;
	}

	public static RecruiterProfile toEntity(CreateRecruiterProfileDto dto) {

		if (dto == null) {
			return null;
		}

		RecruiterProfile recruiterProfile = new RecruiterProfile();

		recruiterProfile.setCompanyName(dto.getCompanyName());
		recruiterProfile.setDesignation(dto.getDesignation());
		recruiterProfile.setDepartment(dto.getDepartment());
		recruiterProfile.setYearsOfExperience(dto.getYearsOfExperience());
		recruiterProfile.setWorkLocation(dto.getWorkLocation());
		recruiterProfile.setLinkedinUrl(dto.getLinkedinUrl());
		recruiterProfile.setRecruiterBio(dto.getRecruiterBio());

		return recruiterProfile;
	}

	public static void updateEntity(RecruiterProfile recruiterProfile, UpdateRecruiterProfileDto dto) {

		if (dto.getCompanyName() != null) {
			recruiterProfile.setCompanyName(dto.getCompanyName());
		}

		if (dto.getDesignation() != null) {
			recruiterProfile.setDesignation(dto.getDesignation());
		}

		if (dto.getDepartment() != null) {
			recruiterProfile.setDepartment(dto.getDepartment());
		}

		if (dto.getYearsOfExperience() != null) {
			recruiterProfile.setYearsOfExperience(dto.getYearsOfExperience());
		}

		if (dto.getWorkLocation() != null) {
			recruiterProfile.setWorkLocation(dto.getWorkLocation());
		}

		if (dto.getLinkedinUrl() != null) {
			recruiterProfile.setLinkedinUrl(dto.getLinkedinUrl());
		}

		if (dto.getRecruiterBio() != null) {
			recruiterProfile.setRecruiterBio(dto.getRecruiterBio());
		}
	}
}