package com.smartjobportal.admin.dto;

import com.smartjobportal.entity.UserStatus;

public class UpdateUserStatusDto {

	private UserStatus accountStatus;

	public UpdateUserStatusDto() {

	}

	public UserStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(UserStatus accountStatus) {

		this.accountStatus = accountStatus;
	}
}