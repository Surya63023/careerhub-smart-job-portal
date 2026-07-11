package com.smartjobportal.candidate.mapper;

import com.smartjobportal.candidate.dto.CandidateProfileDto;
import com.smartjobportal.candidate.dto.CreateCandidateProfileDto;
import com.smartjobportal.candidate.dto.UpdateCandidateProfileDto;
import com.smartjobportal.entity.CandidateProfile;

public class CandidateProfileMapper {

	private CandidateProfileMapper() {
	}

	public static CandidateProfileDto toDto(CandidateProfile profile) {

		if (profile == null) {
			return null;
		}

		CandidateProfileDto dto = new CandidateProfileDto();

		dto.setCandidateProfileId(profile.getCandidateProfileId());

		if (profile.getUser() != null) {
			dto.setUserId(profile.getUser().getUserId());
		}

		dto.setProfileHeadline(profile.getProfileHeadline());
		dto.setProfessionalSummary(profile.getProfessionalSummary());
		dto.setDateOfBirth(profile.getDateOfBirth());
		dto.setGender(profile.getGender());
		dto.setYearsOfExperience(profile.getYearsOfExperience());
		dto.setCurrentLocation(profile.getCurrentLocation());
		dto.setLinkedinUrl(profile.getLinkedinUrl());
		dto.setGithubUrl(profile.getGithubUrl());
		dto.setPortfolioUrl(profile.getPortfolioUrl());
		dto.setHighestQualification(profile.getHighestQualification());
		dto.setCurrentJobTitle(profile.getCurrentJobTitle());
		dto.setExpectedSalary(profile.getExpectedSalary());
		dto.setProfilePicture(profile.getProfilePicture());
		dto.setCreatedAt(profile.getCreatedAt());
		dto.setUpdatedAt(profile.getUpdatedAt());

		return dto;
	}

	public static CandidateProfile toEntity(CreateCandidateProfileDto dto) {

		if (dto == null) {
			return null;
		}

		CandidateProfile profile = new CandidateProfile();

		profile.setProfileHeadline(dto.getProfileHeadline());
		profile.setProfessionalSummary(dto.getProfessionalSummary());

		return profile;
	}

	public static void updateEntity(CandidateProfile profile, UpdateCandidateProfileDto dto) {

		if (dto.getProfileHeadline() != null) {
			profile.setProfileHeadline(dto.getProfileHeadline());
		}

		if (dto.getProfessionalSummary() != null) {
			profile.setProfessionalSummary(dto.getProfessionalSummary());
		}

		if (dto.getDateOfBirth() != null) {
			profile.setDateOfBirth(dto.getDateOfBirth());
		}

		if (dto.getGender() != null) {
			profile.setGender(dto.getGender());
		}

		if (dto.getYearsOfExperience() != null) {
			profile.setYearsOfExperience(dto.getYearsOfExperience());
		}

		if (dto.getCurrentLocation() != null) {
			profile.setCurrentLocation(dto.getCurrentLocation());
		}

		if (dto.getLinkedinUrl() != null) {
			profile.setLinkedinUrl(dto.getLinkedinUrl());
		}

		if (dto.getGithubUrl() != null) {
			profile.setGithubUrl(dto.getGithubUrl());
		}

		if (dto.getPortfolioUrl() != null) {
			profile.setPortfolioUrl(dto.getPortfolioUrl());
		}

		if (dto.getHighestQualification() != null) {
			profile.setHighestQualification(dto.getHighestQualification());
		}

		if (dto.getCurrentJobTitle() != null) {
			profile.setCurrentJobTitle(dto.getCurrentJobTitle());
		}

		if (dto.getExpectedSalary() != null) {
			profile.setExpectedSalary(dto.getExpectedSalary());
		}

		if (dto.getProfilePicture() != null) {
			profile.setProfilePicture(dto.getProfilePicture());
		}
	}
}