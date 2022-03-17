package com.codegym.findJob.service.impl;

import com.codegym.findJob.email.ICompanyNotificationService;
import com.codegym.findJob.model.Apply;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.repository.IApplyRepo;
import com.codegym.findJob.security.userprinciple.InfoPriciple;
import com.codegym.findJob.service.IApplyService;
import com.codegym.findJob.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplyServiceImpl implements IApplyService {

    private IApplyRepo applyRepo;

    private IPostService postService;

    private ICompanyNotificationService companyNotificationService;

    @Override
    public List<Apply> listApply(Users users) {
        return applyRepo.findApplyByUsers(users);
    }

    @Override
    public void addPost(Long postId, Users users) {
        Post post = postService.findPostById(postId);
        Company company = post.getCompany();
        Apply apply = applyRepo.findApplyByUsersAndPost(users, post);
        apply = new Apply();
        apply.setPost(post);
        apply.setUsers(users);
        applyRepo.save(apply);
        companyNotificationService.notificationUserApply(company);
    }

    @Override
    public void deleteApply(Long id) {
        applyRepo.deleteById(id);
    }
}
