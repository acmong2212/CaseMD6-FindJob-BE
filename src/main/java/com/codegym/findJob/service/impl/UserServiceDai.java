package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.UserRepoDai;
import com.codegym.findJob.service.IUserServiceDai;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceDai implements IUserServiceDai {
    UserRepoDai userRepoDai;
    @Override
    public List<Post> findPostByKeyword(String keyword) {
        return userRepoDai.findPostByKeyWord(keyword);
    }

    @Override
    public List<Post> findByFieldAndAddress(String address, Long field_id) {
        return userRepoDai.findByFieldAndAddress(address, field_id);
    }
}
