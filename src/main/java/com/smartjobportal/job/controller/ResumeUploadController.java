package com.smartjobportal.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.smartjobportal.job.dto.ResumeUploadResponseDto;
import com.smartjobportal.job.service.ResumeUploadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/resume-upload")
public class ResumeUploadController {

	@Autowired
	private ResumeUploadService resumeUploadService;

	@PostMapping
	public ResponseEntity<ResumeUploadResponseDto> uploadResume(@RequestParam("file") MultipartFile file) {

		return ResponseEntity.ok(resumeUploadService.uploadResume(file));
	}

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<byte[]> downloadResume(@PathVariable String fileName) {

		byte[] file = resumeUploadService.downloadResume(fileName);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
				.contentType(MediaType.APPLICATION_PDF).body(file);
	}
}