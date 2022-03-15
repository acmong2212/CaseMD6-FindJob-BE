package com.codegym.findJob.repository;

import com.codegym.findJob.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICompanyRepo extends JpaRepository<Company, Long> {
    List<Company> findUsersByIdIsNotLike(Long id);

    Optional<Company> findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Company findByEmailAndPassword(String email, String password);

    @Query(nativeQuery = true, value = "select count(id) from company")
    int countUsers();
}
