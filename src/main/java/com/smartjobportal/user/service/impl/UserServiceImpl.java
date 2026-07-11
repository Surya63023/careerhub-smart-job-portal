package com.smartjobportal.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.User;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.user.dto.ChangePasswordDto;
import com.smartjobportal.user.dto.UpdateUserDto;
import com.smartjobportal.user.dto.UserProfileDto;
import com.smartjobportal.user.dto.UserResponseDto;
import com.smartjobportal.user.mapper.UserMapper;
import com.smartjobportal.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private UserMapper userMapper;

	@Override
	public UserResponseDto getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		return UserMapper.convertToUserResponseDto(user);
	}

	@Override
	public UserResponseDto getUserById(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		return UserMapper.convertToUserResponseDto(user);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {

		List<User> users = userRepository.findAll();

		return users.stream().map(UserMapper::convertToUserResponseDto).collect(Collectors.toList());
	}

	@Override
	public UserResponseDto updateProfile(UpdateUserDto updateUserDto) {

		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String email = authentication.getName();

			System.out.println("EMAIL = " + email);

			User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

			System.out.println("USER ID = " + user.getUserId());

			if (updateUserDto.getFirstName() != null && !updateUserDto.getFirstName().trim().isEmpty()) {

				user.setFirstName(updateUserDto.getFirstName());
			}

			if (updateUserDto.getLastName() != null && !updateUserDto.getLastName().trim().isEmpty()) {

				user.setLastName(updateUserDto.getLastName());
			}

			if (updateUserDto.getPhoneNumber() != null && !updateUserDto.getPhoneNumber().trim().isEmpty()) {

				user.setPhoneNumber(updateUserDto.getPhoneNumber());
			}

			if (updateUserDto.getProfileImage() != null && !updateUserDto.getProfileImage().trim().isEmpty()) {

				user.setProfileImage(updateUserDto.getProfileImage());
			}

			User updatedUser = userRepository.save(user);

			System.out.println("SAVE SUCCESS");

			return UserMapper.convertToUserResponseDto(updatedUser);

		} catch (Exception e) {

			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public String changePassword(ChangePasswordDto changePasswordDto) {

		return null;
	}

	@Override
	public String updateUserStatus(Integer userId, String status) {

		return null;
	}

	@Override
	public UserProfileDto updateCurrentUser(UpdateUserDto updateUserDto) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		if (updateUserDto.getFirstName() != null && !updateUserDto.getFirstName().trim().isEmpty()) {

			user.setFirstName(updateUserDto.getFirstName());
		}

		if (updateUserDto.getLastName() != null && !updateUserDto.getLastName().trim().isEmpty()) {

			user.setLastName(updateUserDto.getLastName());
		}

		if (updateUserDto.getPhoneNumber() != null && !updateUserDto.getPhoneNumber().trim().isEmpty()) {

			user.setPhoneNumber(updateUserDto.getPhoneNumber());
		}

		if (updateUserDto.getProfileImage() != null && !updateUserDto.getProfileImage().trim().isEmpty()) {

			user.setProfileImage(updateUserDto.getProfileImage());
		}

		User updatedUser = userRepository.save(user);

		return UserMapper.convertToUserProfileDto(updatedUser);
	}
}