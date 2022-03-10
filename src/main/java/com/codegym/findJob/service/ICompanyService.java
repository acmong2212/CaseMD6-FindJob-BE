package com.codegym.findJob.service;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;

import java.util.List;

public interface ICompanyService {
    List<Company> findAllCompany();
    void saveCompany(Company company);
    Company findById(Long id);
}
