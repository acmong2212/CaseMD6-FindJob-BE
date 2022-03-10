package com.codegym.findJob.service;


import com.codegym.findJob.model.Role;
import com.codegym.findJob.model.RoleName;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findByName(RoleName name);

}
