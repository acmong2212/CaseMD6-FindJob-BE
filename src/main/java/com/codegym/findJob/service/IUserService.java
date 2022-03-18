package com.codegym.findJob.service;

import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.response.GetJobLocation;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IUserService {
    Boolean existsByEmail(String email);

    Users save(Users users);

    Page<Users> findAll(Pageable pageable);

    String login(SignInFormUser signInForm);

    //Son

    List<Users> findAllUser();

    Users findUserById(long id);

    void saveEdit(Users user);

    List<GetJobLocation> getJobLocation();

    //dai

    List<Post> search(SearchForm searchForm);
}

