package com.smartjobportal.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.security.SecureRandom;

import java.util.Base64;

@Component
public class JwtUtil {

	private Key signingKey;

	private static final SecureRandom secureRandom = new SecureRandom();

//    private static final String SECRET_KEY =
//            "mySecretKeyForJwtAuthenticationMySecretKey12345";

	private static final long JWT_EXPIRATION = 1000 * 60 * 60;

//    private Key getSigningKey() {
//
//        return Keys.hmacShaKeyFor(
//                SECRET_KEY.getBytes()
//        );
//    }

	@PostConstruct
	public void initializeKey() {

		byte[] key = new byte[64];

		secureRandom.nextBytes(key);

		signingKey = Keys.hmacShaKeyFor(key);

		System.out.println("======================================");

		System.out.println("NEW JWT SECRET GENERATED");

		System.out.println(Base64.getEncoder().encodeToString(key));

		System.out.println("======================================");
	}

	public String generateToken(UserDetails userDetails) {

		return Jwts.builder()

				.setSubject(userDetails.getUsername())

				.setIssuedAt(new Date())

				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))

				.signWith(signingKey, SignatureAlgorithm.HS256)

				.compact();
	}

	public String extractUsername(String token) {

		return extractClaims(token).getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {

		String username = extractUsername(token);

		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {

		return extractClaims(token).getExpiration().before(new Date());
	}

	private Claims extractClaims(String token) {

		return Jwts.parserBuilder()

				.setSigningKey(signingKey)

				.build()

				.parseClaimsJws(token)

				.getBody();
	}
}