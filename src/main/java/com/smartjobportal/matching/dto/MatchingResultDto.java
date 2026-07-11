package com.smartjobportal.matching.dto;

import java.util.List;

public class MatchingResultDto {

	private Integer jobId;

	private Integer resumeId;

	private Double matchPercentage;

	private List<MatchedSkillDto> matchedSkills;

	private List<MissingSkillDto> missingSkills;

	private Boolean recommended;

	public MatchingResultDto() {
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public Double getMatchPercentage() {
		return matchPercentage;
	}

	public void setMatchPercentage(Double matchPercentage) {
		this.matchPercentage = matchPercentage;
	}

	public List<MatchedSkillDto> getMatchedSkills() {
		return matchedSkills;
	}

	public void setMatchedSkills(List<MatchedSkillDto> matchedSkills) {
		this.matchedSkills = matchedSkills;
	}

	public List<MissingSkillDto> getMissingSkills() {
		return missingSkills;
	}

	public void setMissingSkills(List<MissingSkillDto> missingSkills) {
		this.missingSkills = missingSkills;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}
}