package com.codegym.findJob.repository;

import com.codegym.findJob.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyPostRepository extends JpaRepository<Post, Long> {
    @Query(nativeQuery = true, value = "select * from post where company_id  = :idCompany")
    List<Post> findPostByCompanyCode(@Param("idCompany") Long idCompany);
}
