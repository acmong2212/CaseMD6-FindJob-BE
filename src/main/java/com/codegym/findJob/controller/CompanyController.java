package com.codegym.findJob.controller;

import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.CompanyService;
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
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllCompany(){
        return new ResponseEntity<>(companyService.findAllCompany(), HttpStatus.OK);
    }

    @GetMapping("/{companyCode}")
    public ResponseEntity<Users> showByCompanyCode(@RequestParam String companyCode){
        return new ResponseEntity<>(companyService.findByCompanyCode(companyCode), HttpStatus.OK);
    }
}
