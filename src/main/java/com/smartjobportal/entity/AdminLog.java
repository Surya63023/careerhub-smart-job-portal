package com.smartjobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_logs")
public class AdminLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_log_id")
	private Integer adminLogId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_user_id")
	private User adminUser;

	@Enumerated(EnumType.STRING)
	@Column(name = "action_type")
	private AdminActionType actionType;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "action_description")
	private String actionDescription;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public AdminLog() {

	}

	public Integer getAdminLogId() {
		return adminLogId;
	}

	public void setAdminLogId(Integer adminLogId) {
		this.adminLogId = adminLogId;
	}

	public User getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}

	public AdminActionType getActionType() {
		return actionType;
	}

	public void setActionType(AdminActionType actionType) {
		this.actionType = actionType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}