package com.smartjobportal.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.smartjobportal.job.dto.CreateResumeDto;
import com.smartjobportal.job.dto.ResumeResponseDto;
import com.smartjobportal.job.dto.UpdateResumeDto;
import com.smartjobportal.job.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	/*
	 * CREATE RESUME
	 */
	@PostMapping
	public ResponseEntity<ResumeResponseDto> createResume(@RequestBody CreateResumeDto createResumeDto) {

		return ResponseEntity.ok(resumeService.createResume(createResumeDto));
	}

	/*
	 * GET MY RESUMES
	 */
	@GetMapping("/my-resumes")
	public ResponseEntity<List<ResumeResponseDto>> getMyResumes() {

		return ResponseEntity.ok(resumeService.getMyResumes());
	}

	/*
	 * GET ACTIVE RESUME
	 */
	@GetMapping("/active")
	public ResponseEntity<ResumeResponseDto> getActiveResume() {

		return ResponseEntity.ok(resumeService.getActiveResume());
	}

	/*
	 * GET RESUME BY ID
	 */
	@GetMapping("/{resumeId}")
	public ResponseEntity<ResumeResponseDto> getResumeById(@PathVariable Integer resumeId) {

		return ResponseEntity.ok(resumeService.getResumeById(resumeId));
	}

	/*
	 * UPDATE RESUME
	 */
	@PutMapping("/{resumeId}")
	public ResponseEntity<ResumeResponseDto> updateResume(@PathVariable Integer resumeId,
			@RequestBody UpdateResumeDto updateResumeDto) {

		return ResponseEntity.ok(resumeService.updateResume(resumeId, updateResumeDto));
	}

	/*
	 * DELETE RESUME
	 */
	@DeleteMapping("/{resumeId}")
	public ResponseEntity<String> deleteResume(@PathVariable Integer resumeId) {

		return ResponseEntity.ok(resumeService.deleteResume(resumeId));
	}

	/*
	 * ACTIVATE RESUME
	 */
	@PutMapping("/{resumeId}/activate")
	public ResponseEntity<ResumeResponseDto> activateResume(@PathVariable Integer resumeId) {

		return ResponseEntity.ok(resumeService.activateResume(resumeId));
	}

	@PutMapping("/{resumeId}/replace-file")
	public ResponseEntity<ResumeResponseDto> replaceResumeFile(@PathVariable Integer resumeId,
			@RequestParam("file") MultipartFile file) {

		return ResponseEntity.ok(resumeService.replaceResumeFile(resumeId, file));
	}

	@GetMapping("/test")
	public String test() {

		return "RESUME API WORKING";
	}
}