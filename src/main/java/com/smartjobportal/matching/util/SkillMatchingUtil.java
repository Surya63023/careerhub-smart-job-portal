package com.smartjobportal.matching.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class SkillMatchingUtil {

	private SkillMatchingUtil() {
	}

	/*
	 * Convert skill to lowercase Remove extra spaces
	 */
	public static String normalizeSkill(String skill) {

		if (skill == null) {
			return "";
		}

		return skill.trim().toLowerCase();
	}

	/*
	 * Convert CSV skill string into Set
	 *
	 * Example: Java, Spring Boot, Docker
	 */
	public static Set<String> convertSkillsToSet(String skills) {

		if (skills == null || skills.isBlank()) {
			return Set.of();
		}

		return Arrays.stream(skills.split(",")).map(SkillMatchingUtil::normalizeSkill).collect(Collectors.toSet());
	}

	/*
	 * Match Percentage Formula
	 */
	public static double calculatePercentage(int matchedSkills, int totalRequiredSkills) {

		if (totalRequiredSkills == 0) {
			return 0.0;
		}

		return ((double) matchedSkills / totalRequiredSkills) * 100;
	}
}