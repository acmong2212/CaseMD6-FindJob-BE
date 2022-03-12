package com.codegym.findJob.service;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {
    Optional<Company> findByEmail(String email);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    Company save(Company company);
    void deleteById(Long id);
    Optional<Company> findById(Long id);
    Page<Company> findAll(Pageable pageable);
    List<Company> findUsersByIdIsNotLike(Long id);
    int countUsers();

    String login(SignInFormUser signInForm);
    List<Company> findAll();
}
