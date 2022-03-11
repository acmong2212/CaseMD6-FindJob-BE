package com.codegym.findJob.repository;

import com.codegym.findJob.model.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
