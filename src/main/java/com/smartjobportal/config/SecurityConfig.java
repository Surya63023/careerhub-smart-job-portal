package com.smartjobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smartjobportal.security.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http

				.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

						// ==========================================
						// PUBLIC ENDPOINTS
						// ==========================================

						.requestMatchers("/", "/favicon.ico", "/html/**", "/css/**", "/js/**", "/images/**").permitAll()

						.requestMatchers("/api/auth/**").permitAll()

						// ==========================================
						// CANDIDATE MODULE
						// ==========================================

						.requestMatchers("/api/candidate/**").hasAuthority("ROLE_CANDIDATE")

						.requestMatchers("/api/resumes/**").hasAuthority("ROLE_CANDIDATE")

						.requestMatchers("/api/resume-upload/**").hasAnyAuthority("ROLE_CANDIDATE", "ROLE_RECRUITER")
						// ==========================================
						// RECRUITER MODULE
						// ==========================================

						.requestMatchers("/api/recruiters/**").hasAuthority("ROLE_RECRUITER")

						.requestMatchers(HttpMethod.GET, "/api/companies/filter")
						.hasAnyAuthority("ROLE_CANDIDATE", "ROLE_RECRUITER")

						.requestMatchers("/api/companies/**").hasAuthority("ROLE_RECRUITER")

						// ==========================================
						// JOB MODULE
						// ==========================================

						.requestMatchers(HttpMethod.GET, "/api/jobs/my-jobs").hasAuthority("ROLE_RECRUITER")

						.requestMatchers(HttpMethod.POST, "/api/jobs").hasAuthority("ROLE_RECRUITER")

						.requestMatchers(HttpMethod.PUT, "/api/jobs/**").hasAuthority("ROLE_RECRUITER")

						.requestMatchers(HttpMethod.DELETE, "/api/jobs/**").hasAuthority("ROLE_RECRUITER")

						.requestMatchers("/api/jobs/**").authenticated()

						// ==========================================
						// APPLICATION MODULE
						// ==========================================

						.requestMatchers(HttpMethod.POST, "/api/applications/apply").hasAuthority("ROLE_CANDIDATE")

						.requestMatchers(HttpMethod.GET, "/api/applications/my-applications")
						.hasAuthority("ROLE_CANDIDATE")

						.requestMatchers(HttpMethod.GET, "/api/applications/job/**").hasAuthority("ROLE_RECRUITER")

						.requestMatchers(HttpMethod.PUT, "/api/applications/*/status").hasAuthority("ROLE_RECRUITER")

						.requestMatchers(HttpMethod.GET, "/api/applications/*/history")
						.hasAnyAuthority("ROLE_CANDIDATE", "ROLE_RECRUITER")

						// ==========================================
						// AI MATCHING
						// ==========================================

						.requestMatchers("/api/ai-match/**").hasAnyAuthority("ROLE_CANDIDATE", "ROLE_RECRUITER")

						// ==========================================
						// ADMIN MODULE
						// ==========================================

						.requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")

						// ==========================================
						// FALLBACK
						// ==========================================
						.requestMatchers("/api/users/**")
						.hasAnyAuthority("ROLE_CANDIDATE", "ROLE_RECRUITER", "ROLE_ADMIN").anyRequest().authenticated())

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}
}