package com.codegym.findJob.controller;

import com.codegym.findJob.model.Field;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
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
    public ResponseEntity<List<Users>> getAllCompany(){
        return new ResponseEntity<>(companyService.findAllCompany(), HttpStatus.OK);
    }

    @GetMapping("/{companyCode}")
    public ResponseEntity<Users> showByCompanyCode(@PathVariable String companyCode){
        return new ResponseEntity<>(companyService.findByCompanyCode(companyCode), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> editCompanyInformation(@RequestBody Users users){
        companyService.saveCompany(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPost(){
        return new ResponseEntity<>(companyPostService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/field")
    public ResponseEntity<List<Field>> getAllField(){
        return new ResponseEntity<>(companyFieldRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        companyPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/companyCode")
//    public ResponseEntity<List<Post>> showPostByCompanyCode(@PathVariable String companyCode){
//        return new ResponseEntity<>(companyPostService.findPostByCompanyCode(companyCode), HttpStatus.OK);
//    }
//
//    @PutMapping("/post")
//    public ResponseEntity<?> setStatusPost(@PathVariable Long id){
//        companyPostService.setStatusPost(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    //@Todo create controller for login/ register
}
