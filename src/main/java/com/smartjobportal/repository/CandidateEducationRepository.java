package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.CandidateEducation;
import com.smartjobportal.entity.CandidateProfile;

public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Integer> {

	List<CandidateEducation> findByCandidateProfile(CandidateProfile candidateProfile);

	void deleteByCandidateProfile(CandidateProfile candidateProfile);
}