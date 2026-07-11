package com.smartjobportal.resumeparser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.smartjobportal.resumeparser.dto.ExperienceDto;

@Component
public class ExperienceParser {

	private static final List<String> DESIGNATION_KEYWORDS = Arrays.asList("Java Developer", "Software Engineer",
			"Backend Developer", "Frontend Developer", "Full Stack Developer", "Java Intern", "Software Intern",
			"Developer");

	public List<ExperienceDto> parse(String resumeText) {

		List<ExperienceDto> experienceList = new ArrayList<>();

		String companyName = extractCompanyName(resumeText);

		String designation = extractDesignation(resumeText);

		String duration = extractDuration(resumeText);

		if (!companyName.isEmpty() || !designation.isEmpty() || !duration.isEmpty()) {

			ExperienceDto experienceDto = new ExperienceDto(companyName, designation, duration);

			experienceList.add(experienceDto);
		}

		return experienceList;
	}

	private String extractDesignation(String text) {

		for (String designation : DESIGNATION_KEYWORDS) {

			if (text.toLowerCase().contains(designation.toLowerCase())) {

				return designation;
			}
		}

		return "";
	}

	private String extractCompanyName(String text) {

		String[] lines = text.split("\\n");

		for (String line : lines) {

			String lowerCaseLine = line.toLowerCase();

			if (lowerCaseLine.contains("technologies") || lowerCaseLine.contains("solutions")
					|| lowerCaseLine.contains("systems") || lowerCaseLine.contains("software")
					|| lowerCaseLine.contains("services") || lowerCaseLine.contains("private limited")
					|| lowerCaseLine.contains("ltd")) {

				return line.trim();
			}
		}

		return "";
	}

	private String extractDuration(String text) {

		Pattern pattern = Pattern.compile("(\\d+\\s+(Month|Months|Year|Years))", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return matcher.group();
		}

		return "";
	}
}