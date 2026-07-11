package com.smartjobportal.matching.dto;

public class JobMatchScoreDto {

	private Integer matchedSkillsCount;

	private Integer requiredSkillsCount;

	private Double matchPercentage;

	public JobMatchScoreDto() {
	}

	public Integer getMatchedSkillsCount() {
		return matchedSkillsCount;
	}

	public void setMatchedSkillsCount(Integer matchedSkillsCount) {
		this.matchedSkillsCount = matchedSkillsCount;
	}

	public Integer getRequiredSkillsCount() {
		return requiredSkillsCount;
	}

	public void setRequiredSkillsCount(Integer requiredSkillsCount) {
		this.requiredSkillsCount = requiredSkillsCount;
	}

	public Double getMatchPercentage() {
		return matchPercentage;
	}

	public void setMatchPercentage(Double matchPercentage) {
		this.matchPercentage = matchPercentage;
	}
}