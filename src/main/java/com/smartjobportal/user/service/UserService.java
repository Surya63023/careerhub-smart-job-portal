package com.smartjobportal.user.service;

import java.util.List;

import com.smartjobportal.user.dto.ChangePasswordDto;
import com.smartjobportal.user.dto.UpdateUserDto;
import com.smartjobportal.user.dto.UserProfileDto;
import com.smartjobportal.user.dto.UserResponseDto;

public interface UserService {

	UserResponseDto getCurrentUser();

	UserResponseDto getUserById(Integer userId);

	UserResponseDto updateProfile(UpdateUserDto updateUserDto);

	String changePassword(ChangePasswordDto changePasswordDto);

	List<UserResponseDto> getAllUsers();

	String updateUserStatus(Integer userId, String status);

	UserProfileDto updateCurrentUser(UpdateUserDto updateUserDto);
}