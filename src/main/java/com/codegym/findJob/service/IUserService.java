package com.codegym.findJob.service;

import com.codegym.findJob.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    void deleteById(Long id);
    Optional<Users> findById(Long id);
    Page<Users> findAll(Pageable pageable);
    List<Users> findUsersByIdIsNotLike(Long id);
    int countUsers();
}
