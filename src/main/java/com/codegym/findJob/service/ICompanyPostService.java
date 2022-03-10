package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface ICompanyPostService {

    void save(Post post);
    List<Post> findAll();

}
