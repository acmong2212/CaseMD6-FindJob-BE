package com.codegym.findJob.controller;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.request.CompanyRegisterReq;
import com.codegym.findJob.dto.response.JwtResponseCompany;
import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.email.IRegistrationService;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Role;
import com.codegym.findJob.model.RoleName;
import com.codegym.findJob.security.userprinciple.UserPrinciple;
import com.codegym.findJob.service.ICompanyService;
import com.codegym.findJob.service.IRoleService;
import lombok.AllArgsConstructor;
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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("company")
@AllArgsConstructor
public class CompanyAuthController {

    private ICompanyService companyService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private IRegistrationService registrationService;

    private IRoleService roleService;

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

        registrationService.registerCompany(company);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInFormUser signInForm){
        String token = companyService.login(signInForm);
        return ResponseEntity.ok(new JwtResponseCompany(token));
    }

}
