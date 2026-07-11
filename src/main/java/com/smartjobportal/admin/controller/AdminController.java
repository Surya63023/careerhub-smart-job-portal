package com.smartjobportal.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smartjobportal.admin.dto.AdminCompanyDto;
import com.smartjobportal.admin.dto.AdminDashboardStatsDto;
import com.smartjobportal.admin.dto.AdminJobDto;
import com.smartjobportal.admin.service.AdminService;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import com.smartjobportal.admin.dto.AdminUserDto;
import com.smartjobportal.admin.dto.UpdateUserStatusDto;
import com.smartjobportal.admin.dto.AdminApplicationDto;
import com.smartjobportal.admin.dto.AdminCandidateDto;
import com.smartjobportal.admin.dto.AdminRecruiterDto;
import com.smartjobportal.admin.dto.AdminLogDto;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/dashboard/stats")
	public ResponseEntity<AdminDashboardStatsDto> getDashboardStats() {

		return ResponseEntity.ok(adminService.getDashboardStats());
	}

	@GetMapping("/users")
	public ResponseEntity<List<AdminUserDto>> getAllUsers() {

		return ResponseEntity.ok(adminService.getAllUsers());
	}

	@PutMapping("/users/{userId}/status")
	public ResponseEntity<AdminUserDto> updateUserStatus(

			@PathVariable Integer userId,

			@RequestBody UpdateUserStatusDto dto) {

		return ResponseEntity.ok(

				adminService.updateUserStatus(userId, dto));
	}

	@GetMapping("/candidates")
	public ResponseEntity<List<AdminCandidateDto>> getAllCandidates() {

		return ResponseEntity.ok(adminService.getAllCandidates());
	}

	@GetMapping("/recruiters")
	public ResponseEntity<List<AdminRecruiterDto>> getAllRecruiters() {

		return ResponseEntity.ok(adminService.getAllRecruiters());
	}

	@GetMapping("/companies")
	public ResponseEntity<List<AdminCompanyDto>> getAllCompanies() {

		return ResponseEntity.ok(adminService.getAllCompanies());
	}

	@GetMapping("/jobs")
	public ResponseEntity<List<AdminJobDto>> getAllJobs() {

		return ResponseEntity.ok(adminService.getAllJobs());
	}

	@GetMapping("/applications")
	public ResponseEntity<List<AdminApplicationDto>> getAllApplications() {

		return ResponseEntity.ok(adminService.getAllApplications());
	}

	@GetMapping("/logs")
	public ResponseEntity<List<AdminLogDto>> getAllLogs() {

		return ResponseEntity.ok(adminService.getAllLogs());
	}
}