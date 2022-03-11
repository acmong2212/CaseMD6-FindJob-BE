package com.codegym.findJob.service;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Company> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByName(String name) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Override
    public Company save(Company company) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Company> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Company> findUsersByIdIsNotLike(Long id) {
        return null;
    }

    @Override
    public int countUsers() {
        return 0;
    }
}
