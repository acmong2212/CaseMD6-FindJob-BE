package com.codegym.findJob.service;

import com.codegym.findJob.model.Users;

import java.util.List;

public interface ICompanyService {
    List<Users> findAllCompany();
    Users findByCompanyCode(String companyCode);
    void saveCompany(Users users);
    Users findById(Long id);
}
