package com.smartjobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.AdminLog;

public interface AdminLogRepository extends JpaRepository<AdminLog, Integer> {

}