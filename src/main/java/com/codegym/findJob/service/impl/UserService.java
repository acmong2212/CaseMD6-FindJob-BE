package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Users;
import com.codegym.findJob.repository.IUserRepo;
import com.codegym.findJob.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepo userRepo;

    @Override
    public List<Users> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Users findUserById(long id) {
        return userRepo.findUsersById(id);
    }

    @Override
    public void saveUser(Users users) {
         userRepo.save(users);
    }
}
