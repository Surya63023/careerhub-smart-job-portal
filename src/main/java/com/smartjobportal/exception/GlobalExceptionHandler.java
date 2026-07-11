package com.smartjobportal.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {

		Map<String, Object> response = new LinkedHashMap<>();

		response.put("timestamp", LocalDateTime.now());

		response.put("message", ex.getMessage());

		response.put("status", HttpStatus.NOT_FOUND.value());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}