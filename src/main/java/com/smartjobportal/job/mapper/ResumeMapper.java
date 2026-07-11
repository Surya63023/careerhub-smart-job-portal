package com.smartjobportal.job.mapper;

import com.smartjobportal.entity.Resume;
import com.smartjobportal.job.dto.ResumeResponseDto;

public class ResumeMapper {

	public static ResumeResponseDto convertToDto(Resume resume) {

		ResumeResponseDto dto = new ResumeResponseDto();

		dto.setResumeId(resume.getResumeId());

		dto.setCandidateName(resume.getCandidateProfile().getUser().getFullName());

		dto.setResumeTitle(resume.getResumeTitle());

		dto.setFileName(resume.getFileName());

		dto.setFilePath(resume.getFilePath());

		dto.setFileType(resume.getFileType());

		dto.setFileSizeKb(resume.getFileSizeKb());

		dto.setParsedSkills(resume.getParsedSkills());

		dto.setParsedExperience(resume.getParsedExperience());

		dto.setParsedEducation(resume.getParsedEducation());

		dto.setIsActive(resume.getIsActive());

		dto.setUploadedAt(resume.getUploadedAt());

		return dto;
	}
}