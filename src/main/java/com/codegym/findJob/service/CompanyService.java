package com.codegym.findJob.service;

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
    public List<Users> findAllCompany() {
        return companyRepository.getAllCompany();
    }

    @Override
    public Users findByCompanyCode(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode);
    }

    @Override
    public void saveCompany(Users users) {
        companyRepository.save(users);
    }

    @Override
    public Users findById(Long id) {
        return companyRepository.findById(id).get();
    }

}
