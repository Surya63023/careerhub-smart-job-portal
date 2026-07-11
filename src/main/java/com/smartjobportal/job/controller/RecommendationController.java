package com.smartjobportal.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartjobportal.job.dto.RecommendedJobDto;
import com.smartjobportal.job.service.RecommendationService;

@RestController
@RequestMapping("/api/jobs")
public class RecommendationController {

	@Autowired
	private RecommendationService recommendationService;

	@GetMapping("/recommendations")
	public ResponseEntity<List<RecommendedJobDto>> getRecommendations() {

		return ResponseEntity.ok(recommendationService.getRecommendations());
	}
}