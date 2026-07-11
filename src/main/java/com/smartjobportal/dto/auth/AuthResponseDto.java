package com.smartjobportal.dto.auth;

public class AuthResponseDto {

    private String token;

    private String type = "Bearer";

    private String role;

    public AuthResponseDto() {

    }

    public AuthResponseDto(
            String token,
            String role) {

        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}