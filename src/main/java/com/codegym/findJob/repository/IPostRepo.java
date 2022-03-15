package com.codegym.findJob.repository;

import com.codegym.findJob.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IPostRepo extends JpaRepository<Post, Long> {
    Post findPostById(Long id);

    @Query(nativeQuery = true, value = "select  * from post where field_id = %:idField% and status = true")
    List<Post> findByField(@Param("idField") Long idField);

    @Query(nativeQuery = true, value = "select * from post where title like %:title% and status = true")
    List<Post> findByTitle(@Param("title") String title);

    @Query(nativeQuery = true, value = "select post.* from post join company on company.id = post.company_id where company.name like %:companyName% and status =  true")
    List<Post> findLikeCompanyName(@Param("companyName") String companyName);

    @Query(nativeQuery = true, value = "select * from post where job_location like %:address% and status = true")
    List<Post> findLikeAddress(@Param("address") String address);

    @Query(nativeQuery = true, value = "select * from post where salary between :minSalary and :maxSalary and status = true")
    List<Post> findBySalary(@Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary);

    @Query(nativeQuery = true, value = "select * from post where salary > :minSalary and status = true")
    List<Post> findByMinSalary(@Param("minSalary") Double minSalary);

    @Query(nativeQuery = true, value = "select * from post where salary < :maxSalary and status = true")
    List<Post> findByMaxSalary(@Param("maxSalary") Double maxSalary);

    @Query(nativeQuery = true, value = "select * from post where company_id  = :idCompany")
    Page<Post> findPostByCompanyCode(@Param("idCompany") Long idCompany, @PageableDefault Pageable pageable);

}

