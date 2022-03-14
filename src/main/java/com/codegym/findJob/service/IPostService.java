package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface IPostService {
    List <Post> findAllPost();
    Post findPostById(long id);

    List<Post> search(String title, String companyName, String address, Long idField, Double minSalary, Double maxSalary);

}
