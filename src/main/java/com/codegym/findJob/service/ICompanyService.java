package com.codegym.findJob.service;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Company save(Company company);

    Optional<Company> findById(Long id);

    Page<Company> findAll(Pageable pageable);

    String login(SignInFormUser signInForm);

    List<Company> findAll();

    void saveEdit(Company company);

    List<Company> findAllByEmailContaining(String email);

    List<Company> highestRecruitmentDemand();

}
