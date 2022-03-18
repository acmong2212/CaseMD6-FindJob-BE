package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPostService {
    Page<Post>findAllPosts(Pageable pageable);

    Post findPostById(Long id);

    Page<Post> findPostByIdCompany(Long idCompany, Pageable pageable);

    void save(Post post);

    void setStatusPost(Long idPost);





}
