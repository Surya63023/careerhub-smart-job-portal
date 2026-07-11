package com.smartjobportal.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.job.dto.CreateJobDto;
import com.smartjobportal.job.dto.JobPageResponseDto;
import com.smartjobportal.job.dto.JobResponseDto;
import com.smartjobportal.job.dto.JobSearchDto;
import com.smartjobportal.job.dto.UpdateJobDto;
import com.smartjobportal.job.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

	@Autowired
	private JobService jobService;

	/*
	 * CREATE JOB
	 */
	@PostMapping
	public ResponseEntity<JobResponseDto> createJob(@Valid @RequestBody CreateJobDto createJobDto) {

		return ResponseEntity.ok(jobService.createJob(createJobDto));
	}

	/*
	 * GET ALL JOBS
	 */
	@GetMapping
	public ResponseEntity<JobPageResponseDto> getAllJobs(

			@RequestParam(defaultValue = "0") int pageNumber,

			@RequestParam(defaultValue = "5") int pageSize,

			@RequestParam(defaultValue = "createdAt") String sortBy,

			@RequestParam(defaultValue = "desc") String sortDirection) {

		return ResponseEntity.ok(jobService.getAllJobs(pageNumber, pageSize, sortBy, sortDirection));
	}

	/*
	 * GET JOB BY ID
	 */
	@GetMapping("/{jobId}")
	public ResponseEntity<JobResponseDto> getJobById(@PathVariable Integer jobId) {

		return ResponseEntity.ok(jobService.getJobById(jobId));
	}

	/*
	 * GET RECRUITER JOBS
	 */
	@GetMapping("/my-jobs")
	public ResponseEntity<List<JobResponseDto>> getRecruiterJobs() {

		return ResponseEntity.ok(jobService.getRecruiterJobs());
	}

	/*
	 * SEARCH JOBS
	 */
	@PostMapping("/search")
	public ResponseEntity<List<JobResponseDto>> searchJobs(@RequestBody JobSearchDto jobSearchDto) {

		return ResponseEntity.ok(jobService.searchJobs(jobSearchDto));
	}

	/*
	 * SEARCH JOBS WITH PAGINATION
	 */
	@PostMapping("/search/paged")
	public ResponseEntity<JobPageResponseDto> searchJobsWithPagination(

			@RequestBody JobSearchDto jobSearchDto,

			@RequestParam(defaultValue = "0") int pageNumber,

			@RequestParam(defaultValue = "5") int pageSize,

			@RequestParam(defaultValue = "createdAt") String sortBy,

			@RequestParam(defaultValue = "desc") String sortDirection) {

		return ResponseEntity.ok(

				jobService.searchJobsWithPagination(

						jobSearchDto,

						pageNumber,

						pageSize,

						sortBy,

						sortDirection));
	}

	/*
	 * UPDATE JOB
	 */
	@PutMapping("/{jobId}")
	public ResponseEntity<JobResponseDto> updateJob(

			@PathVariable Integer jobId,

			@Valid @RequestBody UpdateJobDto updateJobDto) {

		return ResponseEntity.ok(jobService.updateJob(jobId, updateJobDto));
	}

	/*
	 * DELETE JOB
	 */
	@DeleteMapping("/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable Integer jobId) {

		return ResponseEntity.ok(jobService.deleteJob(jobId));
	}
}