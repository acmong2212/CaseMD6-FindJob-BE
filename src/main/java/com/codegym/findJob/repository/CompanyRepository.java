package com.codegym.findJob.repository;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.CompanyShorted;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CompanyRepository extends JpaRepository<Users, Long> {
//    @Query(nativeQuery = true, value = "select * from users where company_code is not null")
//    List<Users> getAllCompany();
//
//    @Query(nativeQuery = true, value = "select * from users where company_code  = :companyCode")
//    Users findByCompanyCode(@Param("companyCode") String companyCode);

    @Query(nativeQuery = true, value = "select count(post.id) as countid,post.company_id from post where post.status = true  group by post.company_id order by countid desc limit 5")
    List<CompanyShorted> findListCompanyShorted();
}
