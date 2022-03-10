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

//    @Override
//    public Post findById(Long id) {
//        return companyPostRepository.getById(id);
//    }

    @Override
    public void save(Post post) {
        String postCode = "POST" + companyService.findById(post.getCompany().getId()).getCompanyCode() + post.getField().getId();
        post.setPostCode(postCode);
        companyPostRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return companyPostRepository.findAll();
    }


}
