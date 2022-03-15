package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.CompanyPostRepository;
import com.codegym.findJob.service.ICompanyPostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CompanyPostService implements ICompanyPostService {
    CompanyPostRepository companyPostRepository;

    @Override
    public Post findById(Long id) {
        return companyPostRepository.findById(id).get();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return companyPostRepository.findAll(pageable);
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
