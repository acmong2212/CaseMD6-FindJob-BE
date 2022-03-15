package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.repository.IUserRepo;
import com.codegym.findJob.service.IUserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService2 implements IUserService2 {

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

    @Override
    public void saveEdit(Users user) {
        if (user.getPassword() == null){
            user.setPassword(findUserById(user.getId()).getPassword());
        }
        userRepo.save(user);
    }

}
