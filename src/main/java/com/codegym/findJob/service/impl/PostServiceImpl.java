package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IPostRepo postRepo;

    @Override
    public Page<Post> findAllPost(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public Post findPostById(long id) {
        return postRepo.findPostById(id);
    }

}
