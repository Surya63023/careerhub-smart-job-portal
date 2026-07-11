package com.smartjobportal.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.job.dto.ApplyJobDto;
import com.smartjobportal.job.dto.JobApplicationResponseDto;

import com.smartjobportal.job.service.JobApplicationService;
import com.smartjobportal.job.dto.ApplicationHistoryDto;
import com.smartjobportal.job.dto.ApplicationStatusUpdateDto;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;

	/*
	 * APPLY JOB
	 */
	@PostMapping("/apply")
	public ResponseEntity<JobApplicationResponseDto> applyJob(@RequestBody ApplyJobDto applyJobDto) {

		return ResponseEntity.ok(jobApplicationService.applyJob(applyJobDto));
	}

	/*
	 * CANDIDATE APPLICATIONS
	 */
	@GetMapping("/my-applications")
	public ResponseEntity<List<JobApplicationResponseDto>> getCandidateApplications() {

		return ResponseEntity.ok(jobApplicationService.getCandidateApplications());
	}

	/*
	 * RECRUITER VIEW APPLICANTS
	 */
	@GetMapping("/job/{jobId}")
	public ResponseEntity<List<JobApplicationResponseDto>> getApplicantsByJob(@PathVariable Integer jobId) {

		return ResponseEntity.ok(jobApplicationService.getApplicantsByJob(jobId));
	}

	@GetMapping("/{applicationId}/history")
	public ResponseEntity<List<ApplicationHistoryDto>> getApplicationHistory(@PathVariable Integer applicationId) {

		return ResponseEntity.ok(jobApplicationService.getApplicationHistory(applicationId));
	}

	/*
	 * RECRUITER UPDATE APPLICATION STATUS
	 */
	@PutMapping("/{applicationId}/status")
	public ResponseEntity<JobApplicationResponseDto> updateApplicationStatus(@PathVariable Integer applicationId,
			@RequestBody ApplicationStatusUpdateDto dto) {

		return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(applicationId, dto));
	}
}