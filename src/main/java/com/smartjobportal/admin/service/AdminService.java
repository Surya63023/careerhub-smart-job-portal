package com.smartjobportal.admin.service;

import com.smartjobportal.admin.dto.AdminDashboardStatsDto;
import com.smartjobportal.admin.dto.AdminJobDto;
import com.smartjobportal.admin.dto.AdminLogDto;

import java.util.List;
import com.smartjobportal.admin.dto.AdminUserDto;
import com.smartjobportal.admin.dto.UpdateUserStatusDto;
import com.smartjobportal.admin.dto.AdminApplicationDto;
import com.smartjobportal.admin.dto.AdminCandidateDto;
import com.smartjobportal.admin.dto.AdminCompanyDto;
import com.smartjobportal.admin.dto.AdminRecruiterDto;

public interface AdminService {

	AdminDashboardStatsDto getDashboardStats();

	List<AdminUserDto> getAllUsers();

	AdminUserDto updateUserStatus(Integer userId, UpdateUserStatusDto dto);

	List<AdminCandidateDto> getAllCandidates();

	List<AdminRecruiterDto> getAllRecruiters();

	List<AdminCompanyDto> getAllCompanies();

	List<AdminJobDto> getAllJobs();

	List<AdminApplicationDto> getAllApplications();

	List<AdminLogDto> getAllLogs();
}