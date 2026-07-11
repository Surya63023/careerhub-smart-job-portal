package com.smartjobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartjobportal.dto.auth.AuthRequestDto;
import com.smartjobportal.dto.auth.AuthResponseDto;
import com.smartjobportal.dto.auth.RegisterRequestDto;
import com.smartjobportal.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

		String response = authService.registerUser(registerRequestDto);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/register-admin")
	public ResponseEntity<String> registerAdmin(

			@Valid @RequestBody RegisterRequestDto registerRequestDto) {

		String response = authService.registerAdmin(registerRequestDto);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> loginUser(@Valid @RequestBody AuthRequestDto authRequestDto) {

		AuthResponseDto authResponse = authService.loginUser(authRequestDto);

		return ResponseEntity.ok(authResponse);
	}
}