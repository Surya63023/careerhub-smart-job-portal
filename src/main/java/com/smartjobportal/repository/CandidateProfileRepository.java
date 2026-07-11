package com.smartjobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.CandidateProfile;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Integer> {

	Optional<CandidateProfile> findByUserUserId(Integer userId);
}