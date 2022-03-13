package com.codegym.findJob.controller;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Field;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.service.ICompanyFieldService;
import com.codegym.findJob.service.ICompanyPostService;
import com.codegym.findJob.service.ICompanyService;
import com.codegym.findJob.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    ICompanyService companyService;

    @Autowired
    ICompanyPostService companyPostService;

    @Autowired
    ICompanyFieldService companyFieldService;

    @Autowired
    IPostService postService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PutMapping("")// sửa công ty
    public ResponseEntity<?> editCompanyInformation(@RequestBody Company company) {
        companyService.saveEdit(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPost() {
        return new ResponseEntity<>(companyPostService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/field") //lấy ra list ngành
    public ResponseEntity<List<Field>> getAllField() {
        return new ResponseEntity<>(companyFieldService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public Post findPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }

    @PostMapping("/post/create") //tạo bài post
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        companyPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/edit/{id}")// sửa 1 bài post
    public ResponseEntity<?> editPost( @PathVariable Long id,@RequestBody Post post){
        post.setId(id);
        companyPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/status/{id}")// sửa trạng thái status
    public  ResponseEntity<?> editStatusPost(@PathVariable Long id){
        companyPostService.setStatusPost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}