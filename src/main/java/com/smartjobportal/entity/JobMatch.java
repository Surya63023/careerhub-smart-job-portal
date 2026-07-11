package com.smartjobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "job_matches")
public class JobMatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_id")
	private Integer matchId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	private Job job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resume_id")
	private Resume resume;

	@Column(name = "match_percentage")
	private Double matchPercentage;

	@Column(name = "matched_skills", columnDefinition = "TEXT")
	private String matchedSkills;

	@Column(name = "missing_skills", columnDefinition = "TEXT")
	private String missingSkills;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public JobMatch() {

	}

	@PrePersist
	public void prePersist() {

		createdAt = LocalDateTime.now();
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}