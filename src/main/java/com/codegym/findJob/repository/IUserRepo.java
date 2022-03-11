package com.codegym.findJob.repository;

import com.codegym.findJob.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<Users, Long> {
    Users findUsersById(Long id);
}
