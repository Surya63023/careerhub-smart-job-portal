package com.smartjobportal.job.service;

import org.springframework.web.multipart.MultipartFile;

import com.smartjobportal.job.dto.ResumeUploadResponseDto;

public interface ResumeUploadService {

	ResumeUploadResponseDto uploadResume(MultipartFile file);

	byte[] downloadResume(String fileName);

	void deleteResumeFile(String fileName);

	String replaceResumeFile(String oldFileName, MultipartFile newFile);
}