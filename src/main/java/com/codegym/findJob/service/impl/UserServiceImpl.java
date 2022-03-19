package com.codegym.findJob.service.impl;

import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.response.GetJobLocation;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.repository.IUserRepo;
import com.codegym.findJob.security.jwt.JwtProvider;
import com.codegym.findJob.security.userprinciple.InfoPriciple;
import com.codegym.findJob.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider token;

    @PersistenceContext
    EntityManager em;

    @Autowired
    IPostRepo postRepo;

    @Autowired
    IUserRepo userRepo;


    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Users save(Users users) {
        return userRepo.save(users);
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public String login(SignInFormUser signInForm) {
        Optional<Users> users = userRepo.findByEmail(signInForm.getEmail());

        if (users.isEmpty()) {
            logger.error("User not exist");
        } else if (encoder.matches(signInForm.getPassword(), users.get().getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("USER_ID", users.get().getId());
//            claims.put("EMAIL", users.get().getEmail());
            claims.put("COMPANY_ID", null);
            claims.put("isCompany", false);
            claims.put("ROLE", users.get().getRoles());

            return token.createToken("USER_TOKEN", claims);
        } else {
            logger.error("Email or password incorrect");
        }
        return null;
    }

    //SON

    @Override
    public List<Users> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Users findUserById(long id) {
        return userRepo.findUsersById(id);
    }

    @Override
    public void saveUser(Users users) {
        userRepo.save(users);
    }

    @Override
    public void saveEdit(Users user) {
        if (user.getPassword() == null){
            user.setPassword(findUserById(user.getId()).getPassword());
        }
        userRepo.save(user);
    }

    @Override
    public Users getUsersPriciple() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof InfoPriciple) {
            return ((InfoPriciple) principal).getUser();
        }
        return null;
    }

    @Override
    public Users findById(Long id) {
        return userRepo.findById(id).get();
    }

    //DAI

    public List<GetJobLocation> getJobLocation() {
        return userRepo.getJobLocation();
    }

    @Override
    public List<Post> search(SearchForm searchForm) {

        if (searchForm.get_title() == null){
            searchForm.set_title("");
        }
        if (searchForm.get_jobLocation() == null){
            searchForm.set_jobLocation("");
        }
        if(searchForm.get_minSalary() == null){
            searchForm.set_minSalary((double) 0);
        }
        if (searchForm.get_maxSalary() == null){
            searchForm.set_maxSalary(Math.pow(10, 308));
        }
        if (searchForm.get_idField() == null){
            System.err.println(searchForm);
            return postRepo.searchPostNoField(searchForm.get_title(), searchForm.get_jobLocation(), searchForm.get_minSalary(), searchForm.get_maxSalary());
        }
        System.err.println(searchForm);
        return postRepo.searchPost(searchForm.get_title(), searchForm.get_jobLocation(), searchForm.get_idField(), searchForm.get_minSalary(),searchForm.get_maxSalary());
    }

    }


