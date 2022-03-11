package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface ICompanyPostService {
    List<Post> findByIdCompany(Long id);
    Post findById(Long id);
    void save(Post post);
    List<Post> findAll();
    void setStatusPost(Long id);

}
