package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.ApplicationStatusHistory;
import com.smartjobportal.entity.JobApplication;

public interface ApplicationStatusHistoryRepository extends JpaRepository<ApplicationStatusHistory, Integer> {

	List<ApplicationStatusHistory> findByJobApplicationOrderByChangedAtDesc(JobApplication jobApplication);

	List<ApplicationStatusHistory> findByJobApplicationApplicationIdOrderByChangedAtDesc(Integer applicationId);
}