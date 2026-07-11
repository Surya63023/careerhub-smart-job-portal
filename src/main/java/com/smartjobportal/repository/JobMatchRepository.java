package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.JobMatch;
import com.smartjobportal.entity.Resume;

public interface JobMatchRepository extends JpaRepository<JobMatch, Integer> {

	/*
	 * All matches for a job
	 */
	List<JobMatch> findByJob(Job job);

	/*
	 * All matches for a resume
	 */
	List<JobMatch> findByResume(Resume resume);

	/*
	 * Check if match already exists
	 */
	boolean existsByJobAndResume(Job job, Resume resume);

	/*
	 * Find specific match
	 */
	JobMatch findByJobAndResume(Job job, Resume resume);

	/*
	 * Ranking candidates by match percentage
	 */
	List<JobMatch> findByJobOrderByMatchPercentageDesc(Job job);

	List<JobMatch> findByResumeResumeId(Integer resumeId);
}