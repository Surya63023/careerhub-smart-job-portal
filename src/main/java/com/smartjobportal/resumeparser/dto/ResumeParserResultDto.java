package com.smartjobportal.resumeparser.dto;

import java.util.List;

public class ResumeParserResultDto {

	private CandidateInfoDto candidateInfo;

	private List<String> skills;

	private List<EducationDto> education;

	private List<ExperienceDto> experience;

	public ResumeParserResultDto() {
	}

	public ResumeParserResultDto(CandidateInfoDto candidateInfo, List<String> skills, List<EducationDto> education,
			List<ExperienceDto> experience) {

		this.candidateInfo = candidateInfo;
		this.skills = skills;
		this.education = education;
		this.experience = experience;
	}

	public CandidateInfoDto getCandidateInfo() {
		return candidateInfo;
	}

	public void setCandidateInfo(CandidateInfoDto candidateInfo) {
		this.candidateInfo = candidateInfo;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<EducationDto> getEducation() {
		return education;
	}

	public void setEducation(List<EducationDto> education) {
		this.education = education;
	}

	public List<ExperienceDto> getExperience() {
		return experience;
	}

	public void setExperience(List<ExperienceDto> experience) {
		this.experience = experience;
	}
}