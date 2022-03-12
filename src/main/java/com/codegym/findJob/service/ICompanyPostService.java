package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface ICompanyPostService {
    Post findById(Long id);
    List<Post> findAll();
    List<Post> findPostByIdCompany(Long idCompany);
    void save(Post post);
    void setStatusPost(Long idPost);

}
