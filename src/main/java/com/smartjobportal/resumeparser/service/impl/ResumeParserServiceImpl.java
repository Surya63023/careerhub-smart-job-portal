package com.smartjobportal.resumeparser.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartjobportal.entity.CandidateEducation;
import com.smartjobportal.entity.CandidateExperience;
import com.smartjobportal.entity.CandidateProfile;
import com.smartjobportal.entity.CandidateSkill;
import com.smartjobportal.entity.Resume;
import com.smartjobportal.repository.CandidateEducationRepository;
import com.smartjobportal.repository.CandidateExperienceRepository;
import com.smartjobportal.repository.CandidateSkillRepository;
import com.smartjobportal.repository.ResumeRepository;
import com.smartjobportal.resumeparser.dto.CandidateInfoDto;
import com.smartjobportal.resumeparser.dto.EducationDto;
import com.smartjobportal.resumeparser.dto.ExperienceDto;
import com.smartjobportal.resumeparser.dto.ResumeParserResultDto;
import com.smartjobportal.resumeparser.extractor.TextExtractionService;
import com.smartjobportal.resumeparser.parser.CandidateInfoParser;
import com.smartjobportal.resumeparser.parser.EducationParser;
import com.smartjobportal.resumeparser.parser.ExperienceParser;
import com.smartjobportal.resumeparser.parser.SkillParser;
import com.smartjobportal.resumeparser.service.ResumeParserService;

@Service
public class ResumeParserServiceImpl implements ResumeParserService {

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private TextExtractionService textExtractionService;

	@Autowired
	private CandidateInfoParser candidateInfoParser;

	@Autowired
	private SkillParser skillParser;

	@Autowired
	private EducationParser educationParser;

	@Autowired
	private ExperienceParser experienceParser;

	@Autowired
	private CandidateSkillRepository candidateSkillRepository;

	@Autowired
	private CandidateEducationRepository candidateEducationRepository;

	@Autowired
	private CandidateExperienceRepository candidateExperienceRepository;

	@Override
	public ResumeParserResultDto parseResume(Integer resumeId) {

		try {

			Resume resume = resumeRepository.findById(resumeId)
					.orElseThrow(() -> new RuntimeException("Resume not found"));

			CandidateProfile candidateProfile = resume.getCandidateProfile();

			File resumeFile = new File(resume.getFilePath());

			String resumeText = textExtractionService.extractText(resumeFile);

			System.out.println("=================================");
			System.out.println("RESUME TEXT");
			System.out.println("=================================");
			System.out.println(resumeText);
			System.out.println("=================================");
			System.out.println("TEXT LENGTH = " + resumeText.length());

			CandidateInfoDto candidateInfo = candidateInfoParser.parse(resumeText);

			List<String> skills = skillParser.parse(resumeText);

			System.out.println("PARSED SKILLS = " + skills);

			List<EducationDto> education = educationParser.parse(resumeText);

			List<ExperienceDto> experience = experienceParser.parse(resumeText);

			resume.setParsedSkills(String.join(", ", skills));

			resumeRepository.save(resume);

			candidateSkillRepository.deleteByCandidateProfile(candidateProfile);

			candidateEducationRepository.deleteByCandidateProfile(candidateProfile);

			candidateExperienceRepository.deleteByCandidateProfile(candidateProfile);

			saveSkills(candidateProfile, skills);

			saveEducation(candidateProfile, education);

			saveExperience(candidateProfile, experience);

			ResumeParserResultDto result = new ResumeParserResultDto();

			result.setCandidateInfo(candidateInfo);

			result.setSkills(skills);

			result.setEducation(education);

			result.setExperience(experience);

			return result;

		} catch (IOException exception) {

			throw new RuntimeException("Failed to parse resume", exception);
		}
	}

	private void saveSkills(CandidateProfile candidateProfile, List<String> skills) {

		for (String skill : skills) {

			CandidateSkill candidateSkill = new CandidateSkill();

			candidateSkill.setCandidateProfile(candidateProfile);

			candidateSkill.setSkillName(skill);

			candidateSkillRepository.save(candidateSkill);
		}
	}

	private void saveEducation(CandidateProfile candidateProfile, List<EducationDto> educationList) {

		for (EducationDto dto : educationList) {

			CandidateEducation education = new CandidateEducation();

			education.setCandidateProfile(candidateProfile);

			education.setDegree(dto.getDegree());

			education.setInstitutionName(dto.getInstitution());

			education.setUniversityName(dto.getInstitution());

			try {

				Integer year = Integer.parseInt(dto.getYear());

				education.setStartYear(year);

				education.setEndYear(year);

			} catch (Exception ignored) {

				education.setStartYear(2000);

				education.setEndYear(2000);
			}

			candidateEducationRepository.save(education);
		}
	}

	private void saveExperience(CandidateProfile candidateProfile, List<ExperienceDto> experienceList) {

		for (ExperienceDto dto : experienceList) {

			CandidateExperience experience = new CandidateExperience();

			experience.setCandidateProfile(candidateProfile);

			experience.setCompanyName(dto.getCompanyName());

			experience.setJobTitle(dto.getDesignation());

			experience.setStartDate(java.time.LocalDate.of(2000, 1, 1));

			experience.setEndDate(java.time.LocalDate.of(2000, 1, 1));

			candidateExperienceRepository.save(experience);
		}
	}
}