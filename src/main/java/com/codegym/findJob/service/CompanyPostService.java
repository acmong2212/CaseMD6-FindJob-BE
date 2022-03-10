package com.codegym.findJob.service;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.CompanyFieldRepository;
import com.codegym.findJob.repository.CompanyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyPostService implements ICompanyPostService {

    @Autowired
    CompanyPostRepository companyPostRepository;

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyFieldRepository companyFieldRepository;



    @Override
    public void save(Post post) {
        String postCode = "POST" + companyService.findById(post.getUsers().getId()).getCompanyCode() + companyFieldRepository.findById(post.getField().getId()).get().getName();
        post.setPostCode(postCode);
        companyPostRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return companyPostRepository.findAll();
    }


}
