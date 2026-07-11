package com.smartjobportal.matching.dto;

public class MatchedSkillDto {

	private String skillName;

	public MatchedSkillDto() {
	}

	public MatchedSkillDto(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}