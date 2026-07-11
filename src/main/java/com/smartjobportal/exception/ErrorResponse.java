package com.smartjobportal.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

	private LocalDateTime timestamp;

	private Integer status;

	private String message;

	private Map<String, String> errors;

	public ErrorResponse() {

	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}