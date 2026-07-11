package com.smartjobportal.job.service;

import java.util.List;

import com.smartjobportal.job.dto.MatchResultDto;
import com.smartjobportal.job.dto.RecommendedJobDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobMatchingService {

	MatchResultDto matchResumeWithJob(Integer jobId, Integer resumeId);

	List<MatchResultDto> getJobMatches(Integer jobId);

	List<RecommendedJobDto> getRecommendedJobs(Integer resumeId);

	Page<RecommendedJobDto> getRecommendedJobs(Integer resumeId, Pageable pageable);
	
	List<MatchResultDto> getAppliedCandidateMatches(
	        Integer jobId);

}