package com.codegym.findJob.service;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
    Users save(Users users);
    void deleteById(Long id);
    Optional<Users> findById(Long id);
    Page<Users> findAll(Pageable pageable);
    List<Users> findUsersByIdIsNotLike(Long id);
    int countUsers();
    String login(SignInFormUser signInForm);
}
