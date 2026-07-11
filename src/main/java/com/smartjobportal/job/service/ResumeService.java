package com.smartjobportal.job.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.smartjobportal.job.dto.CreateResumeDto;
import com.smartjobportal.job.dto.ResumeResponseDto;
import com.smartjobportal.job.dto.UpdateResumeDto;

public interface ResumeService {

	ResumeResponseDto createResume(CreateResumeDto createResumeDto);

	List<ResumeResponseDto> getMyResumes();

	ResumeResponseDto getResumeById(Integer resumeId);

	ResumeResponseDto updateResume(Integer resumeId, UpdateResumeDto updateResumeDto);

	String deleteResume(Integer resumeId);

	ResumeResponseDto activateResume(Integer resumeId);

	ResumeResponseDto getActiveResume();

	ResumeResponseDto replaceResumeFile(Integer resumeId, MultipartFile file);
}