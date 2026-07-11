package com.smartjobportal.resumeparser.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.smartjobportal.resumeparser.dto.CandidateInfoDto;
import com.smartjobportal.resumeparser.util.RegexUtil;

@Component
public class CandidateInfoParser {

	public CandidateInfoDto parse(String resumeText) {

		CandidateInfoDto candidateInfo = new CandidateInfoDto();

		candidateInfo.setName(extractName(resumeText));

		candidateInfo.setEmail(extractEmail(resumeText));

		candidateInfo.setPhoneNumber(extractPhoneNumber(resumeText));

		return candidateInfo;
	}

	private String extractName(String text) {

		String[] lines = text.split("\\n");

		for (String line : lines) {

			String trimmedLine = line.trim();

			if (!trimmedLine.isEmpty()) {
				return trimmedLine;
			}
		}

		return "";
	}

	private String extractEmail(String text) {

		Pattern pattern = Pattern.compile(RegexUtil.EMAIL_REGEX);

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return matcher.group();
		}

		return "";
	}

	private String extractPhoneNumber(String text) {

		Pattern pattern = Pattern.compile(RegexUtil.PHONE_REGEX);

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return matcher.group();
		}

		return "";
	}
}