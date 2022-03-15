package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Field;
import com.codegym.findJob.repository.IFieldRepo;
import com.codegym.findJob.service.IFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FieldService implements IFieldService {
    IFieldRepo iFieldRepo;
    @Override
    public List<Field> findAll() {
        return iFieldRepo.findAll();
    }
}
