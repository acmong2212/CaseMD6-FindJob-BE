package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface IPostServiceDai {
    List<Post> findByField(Long idField);
}
