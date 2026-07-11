package com.smartjobportal.resumeparser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SkillParser {

	private static final List<String> SKILL_KEYWORDS = Arrays.asList("Java", "Spring", "Spring Boot", "Spring MVC",
			"Spring Security", "Hibernate", "JPA", "JDBC", "Servlet", "JSP", "MySQL", "PostgreSQL", "MongoDB", "React",
			"JavaScript", "TypeScript", "HTML", "CSS", "REST API", "Microservices", "Docker", "Kubernetes", "Git",
			"GitHub", "Maven", "Gradle", "AWS");

	 public List<String> parse(String resumeText) {

	        List<String> extractedSkills = new ArrayList<>();

	        String normalizedText = resumeText.toLowerCase();

	        for (String skill : SKILL_KEYWORDS) {

	            boolean found = normalizedText.contains(skill.toLowerCase());

	            System.out.println(skill + " -> " + found);

	            if (found) {
	                extractedSkills.add(skill);
	            }
	        }

	        return extractedSkills;
	    }

	}