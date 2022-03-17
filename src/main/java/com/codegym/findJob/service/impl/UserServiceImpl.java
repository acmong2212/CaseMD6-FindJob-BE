package com.codegym.findJob.service.impl;

import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.dto.request.SignInFormUser;
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
    public Optional<Users> findByEmail(String email) {
        return userRepo.findByEmail(email);
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

    //DAI
    @Override
        public Set<Post> search(SearchForm searchForm) {
            List<Post> byTitle = new ArrayList<>();
            List<Post> byCompanyName = new ArrayList<>();
            List<Post> byAddress = new ArrayList<>();
            List<Post> byIdField = new ArrayList<>();
            List<Post> bySalary = new ArrayList<>();
            Set<Post> listReturn = new HashSet<>();
            List<Post> listCheck =  new ArrayList<>();
        if (searchForm.getTitle() != null){
            byTitle = postRepo.findByTitle(searchForm.getTitle());
            listCheck = byTitle;
        }
            if (searchForm.getCompanyName() != null) {
                byCompanyName = postRepo.findLikeCompanyName(searchForm.getCompanyName());
                listCheck = byCompanyName;
            }
            if (searchForm.getAddress() != null) {
                byAddress = postRepo.findLikeAddress(searchForm.getAddress());
                listCheck = byAddress;
            }
            if (searchForm.getIdField() != null) {
                byIdField = postRepo.findByField(searchForm.getIdField());
                listCheck = byIdField;
            }

            if (searchForm.getMaxSalary() != null && searchForm.getMinSalary() != null) {
                bySalary = postRepo.findBySalary(searchForm.getMinSalary(), searchForm.getMaxSalary());
                listCheck = bySalary;
            }else if (searchForm.getMaxSalary() == null && searchForm.getMinSalary() != null) {
                bySalary = postRepo.findByMinSalary(searchForm.getMinSalary());
                listCheck = bySalary;
            }else if (searchForm.getMaxSalary() != null && searchForm.getMinSalary() == null) {
                bySalary = postRepo.findByMaxSalary(searchForm.getMaxSalary());
                listCheck = bySalary;
            }
            if (listCheck.size() > 0){
                if (byTitle.size() > 0){
                    for (int i = 0; i < byTitle.size(); i++) {
                        for (int j = 0; j < listCheck.size(); j++) {
                            if (byTitle.get(i).getId() == listCheck.get(j).getId()){
                                listReturn.add(byTitle.get(i));
                                break;
                            }
                        }
                    }
                }
                if (byCompanyName.size() > 0){
                    for (int i = 0; i < byCompanyName.size(); i++) {
                        for (int j = 0; j < listCheck.size(); j++) {
                            if (byCompanyName.get(i).getId() == listCheck.get(j).getId()){
                                listReturn.add(byCompanyName.get(i));
                                break;
                            }
                        }
                    }
                }
                if (byAddress.size() > 0){
                    for (int i = 0; i < byAddress.size(); i++) {
                        for (int j = 0; j < listCheck.size(); j++) {
                            if (byAddress.get(i).getId() == listCheck.get(j).getId()){
                                listReturn.add(byAddress.get(i));
                                break;
                            }
                        }
                    }
                }
                if (byIdField.size() > 0){
                    for (int i = 0; i < byIdField.size(); i++) {
                        for (int j = 0; j < listCheck.size(); j++) {
                            if (byIdField.get(i).getId() == listCheck.get(j).getId()){
                                listReturn.add(byIdField.get(i));
                                break;
                            }
                        }
                    }
                }
                if (bySalary.size() > 0){
                    for (int i = 0; i < bySalary.size(); i++) {
                        for (int j = 0; j < listCheck.size(); j++) {
                            if (bySalary.get(i).getId() == listCheck.get(j).getId()){
                                listReturn.add(bySalary.get(i));
                                break;
                            }
                        }
                    }
                }
                return listReturn;
            }else return null;
        }



    }


