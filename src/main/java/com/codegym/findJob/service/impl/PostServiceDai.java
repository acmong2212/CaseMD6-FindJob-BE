package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.service.IPostServiceDai;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class PostServiceDai implements IPostServiceDai {
    IPostRepo postRepo;
    @Override
    public List<Post> findByField(Long idField) {
        return postRepo.findByField(idField);
    }
}
