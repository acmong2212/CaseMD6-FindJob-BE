//package com.codegym.findJob.service;
//
//import com.codegym.findJob.model.Post;
//import com.codegym.findJob.repository.CompanyPostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CompanyPostService implements ICompanyPostService {
//
//    @Autowired
//    CompanyPostRepository companyPostRepository;
//
//    @Override
//    public Post findById(Long id) {
//        return companyPostRepository.getById(id);
//    }
//
//    @Override
//    public void save(Post post) {
//        companyPostRepository.save(post);
//    }
//
//    @Override
//    public List<Post> findAll() {
//        return companyPostRepository.findAll();
//    }
//
//    @Override
//    public List<Post> findPostByPostCode(String postCode) {
//        return companyPostRepository.findPostByCompanyCode(postCode);
//    }
//
//    @Override
//    public void setStatusPost(Long id) {
//        Post post = companyPostRepository.getById(id);
//        post.setStatus(!post.isStatus());
//    }
//
//    @Override
//    public List<Post> findPostByCompanyCode(String companyCode) {
//        return null;
//    }
//}
