package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompanyPostService {
    Post findById(Long id);
    Page<Post> findAll(Pageable pageable);
    List<Post> findPostByIdCompany(Long idCompany);
    void save(Post post);
    void setStatusPost(Long idPost);

}
