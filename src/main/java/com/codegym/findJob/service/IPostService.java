package com.codegym.findJob.service;

import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    Page<Post> findAllPost(Pageable pageable);
    Post findPostById(long id);

    List<Post> search(SearchForm searchForm);

}
