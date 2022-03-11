package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;

import java.util.List;

public interface IUserService2 {
    List<Users> findAllUser();
    Users findUserById(long id);
    void saveUser(Users users);
}
