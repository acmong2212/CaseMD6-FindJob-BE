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
    public Post findById(Long id) {
        return companyPostRepository.findById(id).get();
    }

    @Override
    public void save(Post post) {
        String postCode = "POST" + companyService.findCompanyById(post.getCompany().getId()).get().getCompanyCode() + post.getField().getId();
        post.setPostCode(postCode);
        companyPostRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return companyPostRepository.findAll();
    }

    @Override
    public void setStatusPost(Long id) {
        Post post = findById(id);
        post.setStatus(!post.isStatus());
        save(post);
    }


}
