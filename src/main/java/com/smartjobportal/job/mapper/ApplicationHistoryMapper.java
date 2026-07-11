package com.smartjobportal.job.mapper;

import com.smartjobportal.entity.ApplicationStatusHistory;
import com.smartjobportal.job.dto.ApplicationHistoryDto;

public class ApplicationHistoryMapper {

	public static ApplicationHistoryDto convertToDto(ApplicationStatusHistory history) {

		ApplicationHistoryDto dto = new ApplicationHistoryDto();

		dto.setStatusHistoryId(history.getStatusHistoryId());

		dto.setOldStatus(history.getOldStatus());

		dto.setNewStatus(history.getNewStatus());

		dto.setChangedBy(history.getChangedBy());

		dto.setRemarks(history.getRemarks());

		dto.setChangedAt(history.getChangedAt());

		return dto;
	}
}