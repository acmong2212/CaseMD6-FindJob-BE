package com.codegym.findJob.repository;

import com.codegym.findJob.model.Role;
import com.codegym.findJob.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
