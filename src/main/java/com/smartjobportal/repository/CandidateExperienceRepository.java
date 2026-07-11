package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.CandidateExperience;
import com.smartjobportal.entity.CandidateProfile;

public interface CandidateExperienceRepository extends JpaRepository<CandidateExperience, Integer> {

	List<CandidateExperience> findByCandidateProfile(CandidateProfile candidateProfile);

	void deleteByCandidateProfile(CandidateProfile candidateProfile);
}