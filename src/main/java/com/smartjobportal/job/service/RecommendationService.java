package com.smartjobportal.job.service;

import java.util.List;

import com.smartjobportal.job.dto.RecommendedJobDto;

public interface RecommendationService {

	List<RecommendedJobDto> getRecommendations();
}