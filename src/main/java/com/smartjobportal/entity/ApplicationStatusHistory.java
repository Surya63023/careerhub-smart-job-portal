package com.smartjobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "application_status_history")
public class ApplicationStatusHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_history_id")
	private Integer statusHistoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	private JobApplication jobApplication;

	@Enumerated(EnumType.STRING)
	@Column(name = "old_status")
	private ApplicationStatus oldStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "new_status")
	private ApplicationStatus newStatus;

	@Column(name = "changed_by")
	private String changedBy;

	@Column(name = "changed_at")
	private LocalDateTime changedAt;

	@Column(name = "remarks", columnDefinition = "TEXT")
	private String remarks;

	public ApplicationStatusHistory() {

	}

	@PrePersist
	public void prePersist() {

		if (changedAt == null) {
			changedAt = LocalDateTime.now();
		}
	}

	public Integer getStatusHistoryId() {
		return statusHistoryId;
	}

	public void setStatusHistoryId(Integer statusHistoryId) {
		this.statusHistoryId = statusHistoryId;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	public ApplicationStatus getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(ApplicationStatus oldStatus) {
		this.oldStatus = oldStatus;
	}

	public ApplicationStatus getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(ApplicationStatus newStatus) {
		this.newStatus = newStatus;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}