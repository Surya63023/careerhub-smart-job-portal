package com.smartjobportal.job.dto;

public class MatchResultDto {

	private Integer jobId;

	private Integer resumeId;

	private Double matchPercentage;

	private String matchedSkills;

	private String missingSkills;

	private Integer applicationId;

	private String candidateName;

	private String candidateEmail;

	private String resumeTitle;

	private String fileName;

	private String filePath;

	public MatchResultDto() {
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

	public String getMatchedSkills() {
		return matchedSkills;
	}

	public void setMatchedSkills(String matchedSkills) {
		this.matchedSkills = matchedSkills;
	}

	public String getMissingSkills() {
		return missingSkills;
	}

	public void setMissingSkills(String missingSkills) {
		this.missingSkills = missingSkills;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public String getResumeTitle() {
		return resumeTitle;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public void setResumeTitle(String resumeTitle) {
		this.resumeTitle = resumeTitle;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}