package com.smartjobportal.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_skills")
public class CandidateSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_skill_id")
	private Integer candidateSkillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_profile_id")
	private CandidateProfile candidateProfile;

	@Column(name = "skill_name")
	private String skillName;

	@Enumerated(EnumType.STRING)
	@Column(name = "skill_level")
	private SkillLevel skillLevel;

	@Column(name = "years_of_experience")
	private BigDecimal yearsOfExperience;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {

		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}

		if (skillLevel == null) {
			skillLevel = SkillLevel.BEGINNER;
		}

		if (yearsOfExperience == null) {
			yearsOfExperience = BigDecimal.ZERO;
		}
	}

	public enum SkillLevel {

		BEGINNER, INTERMEDIATE, ADVANCED
	}

	public CandidateSkill() {
	}

	public Integer getCandidateSkillId() {
		return candidateSkillId;
	}

	public void setCandidateSkillId(Integer candidateSkillId) {
		this.candidateSkillId = candidateSkillId;
	}

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public BigDecimal getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(BigDecimal yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}