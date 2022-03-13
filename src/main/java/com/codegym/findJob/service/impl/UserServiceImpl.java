package com.codegym.findJob.service.impl;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.repository.IUserRepository;
import com.codegym.findJob.security.jwt.JwtProvider;
import com.codegym.findJob.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtProvider token;

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.joinTransaction();
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public List<Users> findUsersByIdIsNotLike(Long id) {
        return userRepository.findUsersByIdIsNotLike(id);
    }

    @Override
    public int countUsers() {
        return userRepository.countUsers();
    }

    @Override
    public String login(SignInFormUser signInForm) {
        Optional<Users> users = userRepository.findByEmail(signInForm.getEmail());

        if (users.isEmpty()) {
            logger.error("User not exist");
        } else if (encoder.matches(signInForm.getPassword(), users.get().getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("USER_ID", users.get().getId());
            claims.put("COMPANY_ID", null);
            claims.put("isCompany", false);

            return token.createToken("USER_TOKEN", claims);
        } else {
            logger.error("Email or password incorrect");
        }
        return null;
    }

}
