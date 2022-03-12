package com.codegym.findJob.controller;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Field;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.service.ICompanyFieldService;
import com.codegym.findJob.service.ICompanyPostService;
import com.codegym.findJob.service.ICompanyService;
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


    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PutMapping("")// sửa công ty
    public ResponseEntity<?> editCompanyInformation(@RequestBody Company company) {
        companyService.save(company);
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

    @PostMapping("/post") //tạo bài post
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        companyPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post")// sửa 1 bài post
    public ResponseEntity<?> editPost(@RequestBody Post post){
        companyPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{id}")// sửa trạng thái status
    public  ResponseEntity<?> editStatusPost(@PathVariable Long id){
        companyPostService.setStatusPost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post/{idCompany}")//tìm bài post của 1 công ty tính năn 9
    public ResponseEntity<List<Post>> findPostByIdCompany(@PathVariable Long idCompany){
        return new ResponseEntity<>(companyPostService.findPostByIdCompany(idCompany), HttpStatus.OK);
    }
}