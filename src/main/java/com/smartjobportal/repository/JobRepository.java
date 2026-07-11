package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.RecruiterProfile;

public interface JobRepository extends JpaRepository<Job, Integer> {

	List<Job> findByJobStatus(String jobStatus);

	List<Job> findByRecruiterProfile(RecruiterProfile recruiterProfile);

	@Query("""
			SELECT j
			FROM Job j
			WHERE
			(:keyword IS NULL OR
			    LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))
			    OR
			    LOWER(j.jobDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))
			)
			AND
			(:location IS NULL OR
			    LOWER(j.jobLocation) LIKE LOWER(CONCAT('%', :location, '%'))
			)
			AND
			(:skill IS NULL OR
			    LOWER(j.requiredSkills) LIKE LOWER(CONCAT('%', :skill, '%'))
			)
			AND
			(:employmentType IS NULL OR
			    j.employmentType = :employmentType
			)
			AND
			(:experienceRequired IS NULL OR
			    j.experienceRequired <= :experienceRequired
			)
			""")
	List<Job> searchJobs(

			@Param("keyword") String keyword,

			@Param("location") String location,

			@Param("skill") String skill,

			@Param("employmentType") String employmentType,

			@Param("experienceRequired") Integer experienceRequired);

	@Query("""
			SELECT j
			FROM Job j
			WHERE
			(:keyword IS NULL OR
			    LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))
			    OR LOWER(j.jobDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))
			)
			AND
			(:location IS NULL OR
			    LOWER(j.jobLocation) LIKE LOWER(CONCAT('%', :location, '%'))
			)
			AND
			(:skill IS NULL OR
			    LOWER(j.requiredSkills) LIKE LOWER(CONCAT('%', :skill, '%'))
			)
			AND
			(:employmentType IS NULL OR
			    j.employmentType = :employmentType
			)
			AND
			(:experienceRequired IS NULL OR
			    j.experienceRequired <= :experienceRequired
			)
			AND
			(:jobStatus IS NULL OR
			    j.jobStatus = :jobStatus
			)
			AND
			(:companyName IS NULL OR
			    LOWER(j.company.companyName) LIKE LOWER(CONCAT('%', :companyName, '%'))
			)
			""")
	Page<Job> searchJobsWithPagination(

			@Param("keyword") String keyword,

			@Param("location") String location,

			@Param("skill") String skill,

			@Param("employmentType") String employmentType,

			@Param("experienceRequired") Integer experienceRequired,

			@Param("jobStatus") String jobStatus,

			@Param("companyName") String companyName,

			Pageable pageable);
}