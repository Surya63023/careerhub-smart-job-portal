package com.smartjobportal.admin.dto;

public class AdminDashboardStatsDto {

	private Long totalUsers;

	private Long totalCandidates;

	private Long totalRecruiters;

	private Long totalCompanies;

	private Long totalJobs;

	private Long totalApplications;

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Long getTotalCandidates() {
		return totalCandidates;
	}

	public void setTotalCandidates(Long totalCandidates) {
		this.totalCandidates = totalCandidates;
	}

	public Long getTotalRecruiters() {
		return totalRecruiters;
	}

	public void setTotalRecruiters(Long totalRecruiters) {
		this.totalRecruiters = totalRecruiters;
	}

	public Long getTotalCompanies() {
		return totalCompanies;
	}

	public void setTotalCompanies(Long totalCompanies) {
		this.totalCompanies = totalCompanies;
	}

	public Long getTotalJobs() {
		return totalJobs;
	}

	public void setTotalJobs(Long totalJobs) {
		this.totalJobs = totalJobs;
	}

	public Long getTotalApplications() {
		return totalApplications;
	}

	public void setTotalApplications(Long totalApplications) {
		this.totalApplications = totalApplications;
	}
}