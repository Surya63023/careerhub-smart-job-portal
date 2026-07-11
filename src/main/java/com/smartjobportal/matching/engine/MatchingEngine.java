package com.smartjobportal.matching.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.smartjobportal.matching.dto.MatchedSkillDto;
import com.smartjobportal.matching.dto.MatchingResultDto;
import com.smartjobportal.matching.dto.MissingSkillDto;
import com.smartjobportal.matching.util.SkillMatchingUtil;

@Component
public class MatchingEngine {

	private static final double RECOMMENDATION_THRESHOLD = 70.0;

	public MatchingResultDto performMatching(Integer jobId, Integer resumeId, Set<String> candidateSkills,
			Set<String> jobSkills) {

		List<String> matchedSkills = new ArrayList<>();

		List<String> missingSkills = new ArrayList<>();

		/*
		 * Find matched skills
		 */
		for (String jobSkill : jobSkills) {

			if (candidateSkills.contains(jobSkill)) {
				matchedSkills.add(jobSkill);
			} else {
				missingSkills.add(jobSkill);
			}
		}

		double percentage = SkillMatchingUtil.calculatePercentage(matchedSkills.size(), jobSkills.size());

		MatchingResultDto result = new MatchingResultDto();

		result.setJobId(jobId);

		result.setResumeId(resumeId);

		result.setMatchPercentage(percentage);

		result.setRecommended(percentage >= RECOMMENDATION_THRESHOLD);

		result.setMatchedSkills(matchedSkills.stream().map(MatchedSkillDto::new).collect(Collectors.toList()));

		result.setMissingSkills(missingSkills.stream().map(MissingSkillDto::new).collect(Collectors.toList()));

		return result;
	}
}