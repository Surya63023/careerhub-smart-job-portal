package com.smartjobportal.resumeparser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.smartjobportal.resumeparser.dto.EducationDto;

@Component
public class EducationParser {

	private static final List<String> DEGREE_KEYWORDS = Arrays.asList("B.Tech", "M.Tech", "B.E", "M.E", "BCA", "MCA",
			"B.Sc", "M.Sc", "MBA", "B.Com", "M.Com");

	public List<EducationDto> parse(String resumeText) {

		List<EducationDto> educationList = new ArrayList<>();

		String degree = extractDegree(resumeText);

		String institution = extractInstitution(resumeText);

		String year = extractYear(resumeText);

		if (!degree.isEmpty() || !institution.isEmpty() || !year.isEmpty()) {

			EducationDto educationDto = new EducationDto(degree, institution, year);

			educationList.add(educationDto);
		}

		return educationList;
	}

	private String extractDegree(String text) {

		for (String degree : DEGREE_KEYWORDS) {

			if (text.toLowerCase().contains(degree.toLowerCase())) {

				return degree;
			}
		}

		return "";
	}

	private String extractInstitution(String text) {

		String[] lines = text.split("\\n");

		for (String line : lines) {

			String lowerCaseLine = line.toLowerCase();

			if (lowerCaseLine.contains("university") || lowerCaseLine.contains("college")
					|| lowerCaseLine.contains("institute") || lowerCaseLine.contains("school")) {

				return line.trim();
			}
		}

		return "";
	}

	private String extractYear(String text) {

		Pattern pattern = Pattern.compile("(19|20)\\d{2}");

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return matcher.group();
		}

		return "";
	}
}