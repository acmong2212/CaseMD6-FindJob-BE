package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.ICompanyRepo;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements IPostService {
    
    IPostRepo postRepo;

    ICompanyRepo companyRepo;

    @Override
    public Page<Post> findAllPost(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepo.findPostById(id);
    }

    @Override
    public Page<Post> findPostByIdCompany(Long idCompany, Pageable pageable) {
        return postRepo.findPostByCompanyCode(idCompany, pageable);
    }

    @Override
    public void save(Post post) {
        Long companyId = post.getCompany().getId();
        Optional<Company> company = companyRepo.findById(companyId);
        String postCode = "POST" + company.get().getCompanyCode() + "#" + post.getField().getId();
        post.setPostCode(postCode);
        postRepo.save(post);
    }

    @Override
    public void setStatusPost(Long idPost) {
        Post post = findPostById(idPost);
        post.setStatus(!post.isStatus());
        save(post);
    }

}
