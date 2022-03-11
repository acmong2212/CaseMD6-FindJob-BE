package com.codegym.findJob.controller;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Field;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.CompanyPostService;
//import com.codegym.findJob.service.CompanyPostService;
import com.codegym.findJob.service.CompanyService;
import com.codegym.findJob.repository.CompanyFieldRepository;
import com.codegym.findJob.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ICompanyFieldService companyFieldRepository;


    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.findAllCompany(), HttpStatus.OK);
    }

    @PutMapping("")// sửa công ty
    public ResponseEntity<?> editCompanyInformation(@RequestBody Company company) {
        companyService.saveCompany(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPost() {
        return new ResponseEntity<>(companyPostService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/field") //lấy ra list ngành
    public ResponseEntity<List<Field>> getAllField() {
        return new ResponseEntity<>(companyFieldRepository.findAll(), HttpStatus.OK);
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

    // feature-show-list-post-by-icCompany
    @GetMapping("/post/{idCompany}")
    public ResponseEntity<List<Post>> showListPostByIdCompany(@PathVariable Long idCompany){
        return new ResponseEntity<>(companyPostService.findByIdCompany(idCompany), HttpStatus.OK);
    }
}