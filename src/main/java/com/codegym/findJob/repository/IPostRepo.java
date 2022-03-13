package com.codegym.findJob.repository;

import com.codegym.findJob.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Long> {
    Post findPostById(Long id);

    @Query(nativeQuery = true, value = "select * from post where job_location like %:city% and status = true")
    List<Post> findByCity(@Param("city") String city);
}
