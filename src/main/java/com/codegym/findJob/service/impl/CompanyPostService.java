package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.CompanyPostRepository;
import com.codegym.findJob.service.ICompanyPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CompanyPostService implements ICompanyPostService {
    CompanyPostRepository companyPostRepository;

    @Override
    public Post findById(Long id) {
        Post post = companyPostRepository.findById(id).get();
        System.out.println(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return companyPostRepository.findAll();
    }

    @Override
    public List<Post> findPostByIdCompany(Long idCompany) {
        return companyPostRepository.findPostByCompanyCode(idCompany);
    }

    @Override
    public void save(Post post) {
        companyPostRepository.save(post);
    }

    @Override
    public void setStatusPost(Long idPost) {
        Post post = findById(idPost);
        post.setStatus(!post.isStatus());
        save(post);
    }
}
