package com.smartjobportal.service;

import com.smartjobportal.dto.auth.AuthRequestDto;
import com.smartjobportal.dto.auth.AuthResponseDto;
import com.smartjobportal.dto.auth.RegisterRequestDto;

public interface AuthService {

    String registerUser(RegisterRequestDto registerRequestDto);
    
    String registerAdmin(RegisterRequestDto registerRequestDto);

    AuthResponseDto loginUser(AuthRequestDto authRequestDto);
}