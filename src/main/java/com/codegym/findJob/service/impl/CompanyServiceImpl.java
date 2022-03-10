package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.repository.ICompanyRepository;
import com.codegym.findJob.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyRepository companyRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.joinTransaction();
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public List<Company> findUsersByIdIsNotLike(Long id) {
        return companyRepository.findUsersByIdIsNotLike(id);
    }

    @Override
    public int countUsers() {
        return companyRepository.countUsers();
    }
}
