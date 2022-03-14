package com.codegym.findJob.service;

import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.model.Post;

import java.util.List;

public interface IPostService {
    List <Post> findAllPost();
    Post findPostById(long id);

    List<Post> search(SearchForm searchForm);

}
