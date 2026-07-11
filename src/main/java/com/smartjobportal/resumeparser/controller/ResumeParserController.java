package com.smartjobportal.resumeparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartjobportal.resumeparser.dto.ResumeParserResultDto;
import com.smartjobportal.resumeparser.service.ResumeParserService;

@RestController
@RequestMapping("/api/resume-parser")
public class ResumeParserController {

	@Autowired
	private ResumeParserService resumeParserService;

	@PostMapping("/parse/{resumeId}")
	public ResponseEntity<ResumeParserResultDto> parseResume(@PathVariable Integer resumeId) {

		ResumeParserResultDto result = resumeParserService.parseResume(resumeId);

		return ResponseEntity.ok(result);
	}

	@GetMapping("/result/{resumeId}")
	public ResponseEntity<ResumeParserResultDto> getParsedResult(@PathVariable Integer resumeId) {

		ResumeParserResultDto result = resumeParserService.parseResume(resumeId);

		return ResponseEntity.ok(result);
	}
}