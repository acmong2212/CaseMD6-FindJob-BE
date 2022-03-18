package com.codegym.findJob.repository;

import com.codegym.findJob.dto.response.GetJobLocation;
import com.codegym.findJob.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserRepo extends JpaRepository<Users, Long> {
    Users findUsersById(Long id);

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "select DISTINCT job_location from post")
    List<GetJobLocation> getJobLocation();

}
