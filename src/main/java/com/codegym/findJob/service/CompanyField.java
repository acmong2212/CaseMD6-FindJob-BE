package com.codegym.findJob.service;

import com.codegym.findJob.model.Field;
import com.codegym.findJob.repository.CompanyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyField implements ICompanyFiled{
    @Autowired
    CompanyFieldRepository companyFieldRepository;
    @Override
    public Field findById(Long id) {
        return companyFieldRepository.findById(id).get();
    }
}
