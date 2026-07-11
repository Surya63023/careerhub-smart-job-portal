package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

	List<Resume> findByCandidateProfile(CandidateProfile candidateProfile);

	List<Resume> findByCandidateProfileAndIsActive(CandidateProfile candidateProfile, Boolean isActive);
}