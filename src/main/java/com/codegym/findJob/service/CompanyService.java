package com.codegym.findJob.service;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }
}
