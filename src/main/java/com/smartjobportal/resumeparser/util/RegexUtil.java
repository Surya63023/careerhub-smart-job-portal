package com.smartjobportal.resumeparser.util;

public final class RegexUtil {

	private RegexUtil() {
	}

	public static final String EMAIL_REGEX = "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+";

	public static final String PHONE_REGEX = "\\b\\d{10}\\b";

}