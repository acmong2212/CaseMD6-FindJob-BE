package com.codegym.findJob.repository;

import com.codegym.findJob.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyFieldRepository extends JpaRepository<Field, Long> {
}
