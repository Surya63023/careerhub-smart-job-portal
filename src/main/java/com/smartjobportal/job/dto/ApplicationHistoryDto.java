package com.smartjobportal.job.dto;

import java.time.LocalDateTime;

import com.smartjobportal.entity.ApplicationStatus;

public class ApplicationHistoryDto {

    private Integer statusHistoryId;

    private ApplicationStatus oldStatus;

    private ApplicationStatus newStatus;

    private String changedBy;

    private String remarks;

    private LocalDateTime changedAt;

    public ApplicationHistoryDto() {

    }

    public Integer getStatusHistoryId() {
        return statusHistoryId;
    }

    public void setStatusHistoryId(Integer statusHistoryId) {
        this.statusHistoryId = statusHistoryId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}