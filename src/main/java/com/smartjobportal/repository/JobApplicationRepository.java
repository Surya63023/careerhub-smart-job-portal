package com.smartjobportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.JobApplication;
import com.smartjobportal.entity.Resume;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

	boolean existsByCandidateProfileAndJob(CandidateProfile candidateProfile, Job job);

	List<JobApplication> findByCandidateProfileOrderByUpdatedAtDesc(CandidateProfile candidateProfile);

	List<JobApplication> findByJob(Job job);

	Optional<JobApplication> findByCandidateProfileAndJob(CandidateProfile candidateProfile, Job job);

	boolean existsByResume(Resume resume);
	
	List<JobApplication> findAll();
}