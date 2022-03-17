package com.codegym.findJob.service;

import com.codegym.findJob.model.Apply;
import com.codegym.findJob.model.Users;

import java.util.List;

public interface IApplyService {
    List<Apply> listApply(Users users);
    void addPost(Long postId, Users users);
    void deleteApply(Long id);
}
