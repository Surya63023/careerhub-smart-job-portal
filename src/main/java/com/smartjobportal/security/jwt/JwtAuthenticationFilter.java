package com.smartjobportal.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		String path = request.getServletPath();

		return path.equals("/api/auth/register") || path.equals("/api/auth/login");
	}

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String jwtToken = null;

		String username = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			jwtToken = authorizationHeader.substring(7);

			try {

				username = jwtUtil.extractUsername(jwtToken);

			} catch (IllegalArgumentException e) {

				System.out.println("Unable to get JWT Token");

			} catch (ExpiredJwtException e) {

				System.out.println("JWT Token has expired");

			} catch (MalformedJwtException e) {

				System.out.println("Invalid JWT Token");

			} catch (UnsupportedJwtException e) {

				System.out.println("Unsupported JWT Token");

			} catch (SignatureException e) {

				System.out.println("JWT Signature validation failed");
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);

				System.out.println("=================================");
				System.out.println("JWT USER : " + username);
				System.out.println("AUTHORITIES : " + userDetails.getAuthorities());
				System.out.println("=================================");
			}
		}

		filterChain.doFilter(request, response);
	}
}