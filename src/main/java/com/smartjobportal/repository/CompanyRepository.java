package com.smartjobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartjobportal.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> findAllByOrderByCompanyNameAsc();

    @Query("""
            SELECT c
            FROM Company c
            WHERE c.isVerified = true
            ORDER BY c.companyName ASC
            """)
    List<Company> findAllVerifiedCompanies();

}