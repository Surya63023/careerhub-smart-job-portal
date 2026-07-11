package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Integer> {

	List<CandidateSkill> findByCandidateProfile(CandidateProfile candidateProfile);

	void deleteByCandidateProfile(CandidateProfile candidateProfile);

	List<CandidateSkill> findByCandidateProfileCandidateProfileId(Integer candidateProfileId);
}