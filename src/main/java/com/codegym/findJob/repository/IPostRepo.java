package com.codegym.findJob.repository;

import com.codegym.findJob.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
}
