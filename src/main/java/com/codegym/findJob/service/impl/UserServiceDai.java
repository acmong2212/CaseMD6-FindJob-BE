package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.repository.UserRepoDai;
import com.codegym.findJob.service.IUserServiceDai;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceDai implements IUserServiceDai {
    UserRepoDai userRepoDai;
    IPostRepo postRepo;
    @Override
    public List<Post> findPostByKeyword(String keyword) {
        System.err.println(keyword);
        return userRepoDai.findPostByKeyWord(keyword);
    }

    @Override
    public List<Post> findByCity(String city) {
        return postRepo.findByCity(city);
    }
}
