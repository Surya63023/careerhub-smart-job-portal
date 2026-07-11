package com.smartjobportal.resumeparser.util;

public final class ResumeParsingUtil {

	private ResumeParsingUtil() {
	}

	public static String cleanText(String text) {

		if (text == null) {
			return "";
		}

		return text.replaceAll("\\r", "").replaceAll("[ ]{2,}", " ").replaceAll("\\n{3,}", "\n\n").trim();
	}
}