package com.codegym.findJob.repository;

import com.codegym.findJob.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Users, Long> {
    @Query(nativeQuery = true, value = "select * from users where company_code is not null")
    List<Users> getAllCompany();

    @Query(nativeQuery = true, value = "select * from users where company_code  = :companyCode")
    Users findByCompanyCode(@Param("companyCode") String companyCode);
}
