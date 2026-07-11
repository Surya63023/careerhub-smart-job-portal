package com.smartjobportal.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.job.dto.MatchResultDto;
import com.smartjobportal.job.dto.RecommendedJobDto;
import com.smartjobportal.job.service.JobMatchingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/ai-match")
public class JobMatchingController {

	@Autowired
	private JobMatchingService jobMatchingService;

	@PostMapping("/job/{jobId}/resume/{resumeId}")
	public ResponseEntity<MatchResultDto> matchResumeWithJob(

			@PathVariable Integer jobId,

			@PathVariable Integer resumeId) {

		return ResponseEntity.ok(

				jobMatchingService.matchResumeWithJob(jobId, resumeId));
	}

	@GetMapping("/job/{jobId}")
	public ResponseEntity<List<MatchResultDto>> getJobMatches(

			@PathVariable Integer jobId) {

		return ResponseEntity.ok(

				jobMatchingService.getJobMatches(jobId));
	}

	@GetMapping("/recommendations/{resumeId}")
	public ResponseEntity<List<RecommendedJobDto>> getRecommendations(

			@PathVariable Integer resumeId) {

		return ResponseEntity.ok(

				jobMatchingService.getRecommendedJobs(resumeId));
	}

	@GetMapping("/recommendations")
	public ResponseEntity<Page<RecommendedJobDto>> getRecommendations(

			@RequestParam Integer resumeId,

			@RequestParam(defaultValue = "0") int page,

			@RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);

		return ResponseEntity.ok(

				jobMatchingService.getRecommendedJobs(resumeId, pageable));
	}

	@GetMapping("/job/{jobId}/applied-candidates")
	public ResponseEntity<List<MatchResultDto>> getAppliedCandidateMatches(

			@PathVariable Integer jobId) {

		return ResponseEntity.ok(

				jobMatchingService.getAppliedCandidateMatches(jobId));
	}
}