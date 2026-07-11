package com.smartjobportal.matching.dto;

public class MissingSkillDto {

	private String skillName;

	public MissingSkillDto() {
	}

	public MissingSkillDto(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}