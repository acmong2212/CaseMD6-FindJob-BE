package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface ICompanyPostService {
//    Post findById(Long id);
    void save(Post post);
    List<Post> findAll();
//    List<Post> findPostByPostCode(String postCode);
//    void setStatusPost(Long id);
//    List<Post> findPostByCompanyCode(String companyCode);
}
