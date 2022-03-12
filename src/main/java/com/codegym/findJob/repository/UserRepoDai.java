package com.codegym.findJob.repository;

import com.codegym.findJob.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepoDai extends JpaRepository<Post, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM `case-md6-findjob`.post join company on post.company_id = company.id where company.name like %:keyword%")
    List<Post> findPostByKeyWord(@Param("keyword") String keyword);
}
