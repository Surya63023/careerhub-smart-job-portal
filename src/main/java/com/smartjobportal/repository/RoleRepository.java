package com.smartjobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartjobportal.entity.Role;

public interface RoleRepository
        extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);

}