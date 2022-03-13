package com.codegym.findJob.controller;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.request.CompanyRegisterReq;
import com.codegym.findJob.dto.response.JwtResponse;
import com.codegym.findJob.dto.response.JwtResponseCompany;
import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("company")
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

    @PostMapping("/register")
    public ResponseEntity<?> registerCompany (@Valid @RequestBody CompanyRegisterReq request) {
        if(companyService.existsByName(request.getName())){
            return new ResponseEntity<>(new ResponseMessage("name_existed"), HttpStatus.BAD_REQUEST);
        }
        if(companyService.existsByEmail(request.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("email_existed"), HttpStatus.BAD_REQUEST);
        }

        String text = request.getName();
        char[] companyCode = new char[3];
        text.getChars(0, 3, companyCode, 0);

        Random number = new Random();
        int numberRandom = 9999;
        int numberResult = Integer.parseInt(String.valueOf(number.nextInt(numberRandom)));
        String companyCodeResult = String.valueOf(companyCode) + "#" + numberResult;

        Company company = new Company(
                request.getName(),
                request.getEmail(),
                request.getDescription(),
                request.getAvatar(),
                passwordEncoder.encode(request.getPassword()));
                company.setCompanyCode(companyCodeResult);
        System.out.println(companyCodeResult);

        companyService.save(company);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInFormUser signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = companyService.login(signInForm);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Company company = companyService.findByEmail(userPrinciple.getEmail()).get();
        return ResponseEntity.ok(new JwtResponseCompany(token, company));
    }

//    @PostMapping("/update")
//    public ResponseEntity<String> update(Authentication auth){
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.getAuthentication();
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
