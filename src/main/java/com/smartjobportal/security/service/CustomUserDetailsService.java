package com.smartjobportal.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.User;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.security.userdetails.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		System.out.println("ROLE FROM DB = " + user.getRole());
		
		return new UserPrincipal(user.getEmail(), user.getPassword(), user.getRole());
	}
}