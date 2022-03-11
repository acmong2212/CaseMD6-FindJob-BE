package com.codegym.findJob.controller;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.request.SignUpFormUser;
import com.codegym.findJob.dto.response.JwtResponse;
import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.email.IRegistrationService;
import com.codegym.findJob.model.Role;
import com.codegym.findJob.model.RoleName;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.security.jwt.JwtProvider;
import com.codegym.findJob.security.userprinciple.UserPrinciple;
import com.codegym.findJob.service.IRoleService;
import com.codegym.findJob.service.IUserService;
import lombok.AllArgsConstructor;
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
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserAuthController {

    private IUserService userService;

    private IRoleService roleService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtProvider jwtProvider;

    private IRegistrationService registrationService;

    @PostMapping("/signup/user")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignUpFormUser signUpFormUser) {
        if(userService.existsByEmail(signUpFormUser.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("email_existed"), HttpStatus.OK);
        }
        if(signUpFormUser.getAvatar() == null || signUpFormUser.getAvatar().trim().isEmpty()){
            signUpFormUser.setAvatar("https://firebasestorage.googleapis.com/v0/b/casemd4-b5188.appspot.com/o/Avatar-Facebook-trắng.jpg?alt=media&token=e8460146-9763-4a7f-bb4e-56148b670434"); //Chỗ này là ảnh của user lúc đăng kí, sẽ gán cho nó 1 cái mặc định
        }
        Users users = new Users(
          signUpFormUser.getName(),
          signUpFormUser.getEmail(),
          signUpFormUser.getPhoneNumber(),
          signUpFormUser.getAvatar(),
          passwordEncoder.encode(signUpFormUser.getPassword()));
        Set<String> strRoles = signUpFormUser.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "ADMIN" :
                    Role smRole = roleService.findByName(RoleName.ADMIN).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(smRole);
                    break;
                default:
                    Role usRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(usRole);
            }
        });
        users.setRoles(roles);
        registrationService.register(users);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin/user")
    public ResponseEntity<?> login(@Valid @RequestBody SignInFormUser signUpFormUser){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpFormUser.getEmail(), signUpFormUser.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Users users = userService.findByEmail(userPrinciple.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(token, users));
    }
}
