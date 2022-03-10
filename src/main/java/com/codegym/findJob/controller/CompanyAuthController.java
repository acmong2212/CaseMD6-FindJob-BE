package com.codegym.findJob.controller;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.request.SignUpFormCompany;
import com.codegym.findJob.dto.response.JwtResponseCompany;
import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.security.jwt.JwtProvider;
import com.codegym.findJob.security.userprinciple.UserPrinciple;
import com.codegym.findJob.service.ICompanyService;
import com.codegym.findJob.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*")
public class CompanyAuthController {

    @Autowired
    ICompanyService companyService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup/company")
    public ResponseEntity<?> registerCompany (@Valid @RequestBody SignUpFormCompany signUpFormCompany) {
        if(companyService.existsByName(signUpFormCompany.getName())){
            return new ResponseEntity<>(new ResponseMessage("name_existed"), HttpStatus.OK);
        }
        if(companyService.existsByEmail(signUpFormCompany.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("email_existed"), HttpStatus.OK);
        }

        String text = signUpFormCompany.getName();
        char[] companyCode = new char[30];
        text.getChars(0, 3, companyCode, 0);

        Random number = new Random();
        int numberRandom = 9999;
        int numberResult = Integer.parseInt(String.valueOf(number.nextInt(numberRandom)));
        String companyCodeResult = String.valueOf(companyCode) + "#" + numberResult;

        Company company = new Company(
                signUpFormCompany.getName(),
                signUpFormCompany.getEmail(),
                signUpFormCompany.getDescription(),
                signUpFormCompany.getAddress(),
                signUpFormCompany.getNumberOfEmployees(),
                signUpFormCompany.getBranch(),
                signUpFormCompany.getMapLink(),
                signUpFormCompany.getWebsite(),
                signUpFormCompany.getPhoneNumber(),
                signUpFormCompany.getAvatar(),
                passwordEncoder.encode(signUpFormCompany.getPassword()));
                company.setCompanyCode(companyCodeResult);
        System.out.println(companyCodeResult);

        companyService.save(company);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin/company")
    public ResponseEntity<?> login(@Valid @RequestBody SignInFormUser signInForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Company company = companyService.findByEmail(userPrinciple.getEmail()).get();
        return ResponseEntity.ok(new JwtResponseCompany(token, company));
    }
}
