package com.smartjobportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartjobportal.dto.auth.AuthRequestDto;
import com.smartjobportal.dto.auth.AuthResponseDto;
import com.smartjobportal.dto.auth.RegisterRequestDto;
import com.smartjobportal.entity.User;
import com.smartjobportal.entity.UserStatus;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.security.jwt.JwtUtil;
import com.smartjobportal.security.service.CustomUserDetailsService;
import com.smartjobportal.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public String registerUser(RegisterRequestDto registerRequestDto) {

		if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {

			throw new RuntimeException("Email already exists");
		}

		User user = new User();

		user.setFirstName(registerRequestDto.getFirstName());

		user.setLastName(registerRequestDto.getLastName());

		user.setEmail(registerRequestDto.getEmail());

		user.setPhoneNumber(registerRequestDto.getPhoneNumber());

		user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

		user.setRole("ROLE_CANDIDATE");

		user.setAccountStatus(UserStatus.ACTIVE);

		userRepository.save(user);

		return "User registered successfully";
	}

	@Override
	public String registerAdmin(RegisterRequestDto registerRequestDto) {

		if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {

			throw new RuntimeException("Email already exists");
		}

		User user = new User();

		user.setFirstName(registerRequestDto.getFirstName());

		user.setLastName(registerRequestDto.getLastName());

		user.setEmail(registerRequestDto.getEmail());

		user.setPhoneNumber(registerRequestDto.getPhoneNumber());

		user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

		user.setRole("ROLE_ADMIN");

		user.setAccountStatus(UserStatus.ACTIVE);

		userRepository.save(user);

		return "Admin registered successfully";
	}

	@Override
	public AuthResponseDto loginUser(AuthRequestDto authRequestDto) {

		authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(

						authRequestDto.getEmail(),

						authRequestDto.getPassword()));

		User user = userRepository.findByEmail(authRequestDto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequestDto.getEmail());

		String jwtToken = jwtUtil.generateToken(userDetails);

		return new AuthResponseDto(jwtToken, user.getRole());
	}
}