package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Field;
import com.codegym.findJob.repository.CompanyFieldRepository;
import com.codegym.findJob.service.ICompanyFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;

@Service
@AllArgsConstructor
public class CompanyFieldService implements ICompanyFieldService {
    CompanyFieldRepository companyFieldRepository;
    @Override
    public List<Field> findAll() {
        return companyFieldRepository.findAll();
    }
}
