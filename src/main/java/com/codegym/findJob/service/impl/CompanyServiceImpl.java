package com.codegym.findJob.service.impl;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.repository.ICompanyRepository;
import com.codegym.findJob.security.jwt.JwtProvider;
import com.codegym.findJob.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
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
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider token;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Company> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public Company save(Company company) {
        return repo.save(company);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.joinTransaction();
        repo.deleteById(id);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Company> findUsersByIdIsNotLike(Long id) {
        return repo.findUsersByIdIsNotLike(id);
    }

    @Override
    public int countUsers() {
        return repo.countUsers();
    }

    @Override
    public String login(SignInFormUser signInForm) {
        Optional<Company> cpn = repo.findByEmail(signInForm.getEmail());

        if(cpn.isEmpty()){
            //@Todo thrown exception Company not exist
        }else if(encoder.matches(signInForm.getPassword(),cpn.get().getPassword())){

            Map<String, Object> claims = new HashMap<>();
            claims.put("USER_ID",null);
            claims.put("COMPANY_ID",cpn.get().getId());
            claims.put("isCompany",true);

            return token.createToken("COMPANY_TOKEN",claims);
        }else {
            //@TODO email or password incorrect
        }
        return null;
    }

    @Override
    public List<Company> findAll() {
        return repo.findAll();
    }
}
