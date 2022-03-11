package com.codegym.findJob.service;

import com.codegym.findJob.model.Field;
import com.codegym.findJob.repository.CompanyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyFieldService implements ICompanyFieldService{
    @Autowired
    CompanyFieldRepository companyFieldRepository;
    @Override
    public List<Field> findAll() {
        return companyFieldRepository.findAll();
    }

    @Override
    public Field findById(Long id) {
        return companyFieldRepository.findById(id).get();
    }
}
