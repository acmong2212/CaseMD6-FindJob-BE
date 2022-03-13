package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;

import java.util.List;

public interface IUserServiceDai {
    List<Post> findPostByKeyword(String keyword);
    List<Post> findByFieldAndAddress(String address, Long field_id);
}
