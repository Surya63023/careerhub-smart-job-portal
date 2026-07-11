package com.smartjobportal.resumeparser.service;

import com.smartjobportal.resumeparser.dto.ResumeParserResultDto;

public interface ResumeParserService {

	ResumeParserResultDto parseResume(Integer resumeId);

}