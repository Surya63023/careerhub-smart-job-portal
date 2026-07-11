package com.smartjobportal.job.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartjobportal.job.dto.ResumeUploadResponseDto;
import com.smartjobportal.job.service.ResumeUploadService;

@Service
public class ResumeUploadServiceImpl implements ResumeUploadService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Override
	public ResumeUploadResponseDto uploadResume(MultipartFile file) {

		try {

			/*
			 * Empty file validation	
			 */
			if (file.isEmpty()) {

				throw new RuntimeException("Please select a file");
			}

			/*
			 * File type validation
			 */
			String originalFileName = file.getOriginalFilename();

			String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toUpperCase();

			if (!extension.equals("PDF") && !extension.equals("DOC") && !extension.equals("DOCX")) {

				throw new RuntimeException("Only PDF, DOC and DOCX files are allowed");
			}

			/*
			 * Unique file name
			 */
			String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

			/*
			 * Upload directory
			 */
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {

				Files.createDirectories(uploadPath);
			}

			/*
			 * Save file
			 */
			Path filePath = uploadPath.resolve(uniqueFileName);

			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			ResumeUploadResponseDto response = new ResumeUploadResponseDto();

			response.setFileName(uniqueFileName);

			response.setFilePath(filePath.toString());

			response.setMessage("Resume uploaded successfully");

			return response;

		} catch (IOException exception) {

			throw new RuntimeException("Failed to upload file", exception);
		}

	}

	@Override
	public byte[] downloadResume(String fileName) {

		try {

			Path filePath = Paths.get(uploadDir).resolve(fileName);

			return Files.readAllBytes(filePath);

		} catch (IOException exception) {

			throw new RuntimeException("File not found", exception);
		}
	}

	@Override
	public void deleteResumeFile(String fileName) {

		try {

			Path filePath = Paths.get(uploadDir).resolve(fileName);

			if (Files.exists(filePath)) {

				Files.delete(filePath);
			}

		} catch (IOException exception) {

			throw new RuntimeException("Failed to delete resume file", exception);
		}
	}

	@Override
	public String replaceResumeFile(String oldFileName, MultipartFile newFile) {

		try {

			/*
			 * Delete old file
			 */
			deleteResumeFile(oldFileName);

			/*
			 * Validate file
			 */
			if (newFile.isEmpty()) {

				throw new RuntimeException("Please select a file");
			}

			String originalFileName = newFile.getOriginalFilename();

			String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toUpperCase();

			if (!extension.equals("PDF") && !extension.equals("DOC") && !extension.equals("DOCX")) {

				throw new RuntimeException("Only PDF, DOC and DOCX files are allowed");
			}

			/*
			 * Create upload folder if needed
			 */
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {

				Files.createDirectories(uploadPath);
			}

			/*
			 * New unique file name
			 */
			String newFileName = UUID.randomUUID() + "_" + originalFileName;

			Path filePath = uploadPath.resolve(newFileName);

			Files.copy(newFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			return newFileName;

		} catch (IOException exception) {

			throw new RuntimeException("Failed to replace resume file", exception);
		}
	}
}