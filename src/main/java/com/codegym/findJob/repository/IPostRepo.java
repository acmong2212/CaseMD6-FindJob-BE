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

    @Query(nativeQuery = true, value="select * from post where status = true;")
    Page<Post>findAllPosts(Pageable pageable);

    Post findPostById(Long id);

    @Query(nativeQuery = true, value = "select * from post where company_id  = :idCompany")
    Page<Post> findPostByCompanyCode(@Param("idCompany") Long idCompany, @PageableDefault Pageable pageable);


    @Query(nativeQuery = true, value = "select post.* from post join company on company.id = post.company_id where company.name like  %:title% " +
            "and post.title like %:title% " +
            "and post.job_location like %:jobLocation% " +
            "and post.field_id = :idField " +
            "and (post.min_salary between :minSalary and :maxSalary " +
            "or post.max_salary between :minSalary and :maxSalary) " +
            "and post.status = true")
    List<Post> searchPost(String title, String jobLocation, Long idField, Double minSalary,Double maxSalary);

    @Query(nativeQuery = true, value = "select post.* from post join company on company.id = post.company_id where company.name like  %:title% " +
            "and post.title like %:title% " +
            "and post.job_location like %:jobLocation% " +
            "and (post.min_salary between :minSalary and :maxSalary " +
            "or post.max_salary between :minSalary and :maxSalary) " +
            "and post.status = true")
    List<Post> searchPostNoField(String title, String jobLocation, Double minSalary,Double maxSalary);

}

