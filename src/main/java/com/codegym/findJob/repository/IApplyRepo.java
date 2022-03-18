package com.codegym.findJob.repository;

import com.codegym.findJob.model.Apply;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IApplyRepo extends JpaRepository<Apply, Long> {
    List<Apply>findApplyByUsers(Users users);
    Apply findApplyByUsersAndPost(Users users, Post post);
}
