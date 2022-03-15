package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Role;
import com.codegym.findJob.model.RoleName;
import com.codegym.findJob.repository.IRoleRepo;
import com.codegym.findJob.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepo roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
