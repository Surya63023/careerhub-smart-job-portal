package com.smartjobportal.user.mapper;

import com.smartjobportal.entity.User;
import com.smartjobportal.user.dto.UserProfileDto;
import com.smartjobportal.user.dto.UserResponseDto;

public class UserMapper {

	public static UserResponseDto convertToUserResponseDto(User user) {

		UserResponseDto dto = new UserResponseDto();

		dto.setUserId(user.getUserId());

		dto.setFirstName(user.getFirstName());

		dto.setLastName(user.getLastName());

		dto.setFullName(user.getFullName());

		dto.setEmail(user.getEmail());

		dto.setPhoneNumber(user.getPhoneNumber());

		dto.setProfileImage(user.getProfileImage());

		dto.setAccountStatus(user.getAccountStatus());

		return dto;
	}

	public static UserProfileDto convertToUserProfileDto(User user) {

		UserProfileDto dto = new UserProfileDto();

		dto.setUserId(user.getUserId());

		dto.setFirstName(user.getFirstName());

		dto.setLastName(user.getLastName());

		dto.setFullName(user.getFullName());

		dto.setEmail(user.getEmail());

		dto.setPhoneNumber(user.getPhoneNumber());

		dto.setProfileImage(user.getProfileImage());

		dto.setAccountStatus(user.getAccountStatus());

		return dto;
	}
}