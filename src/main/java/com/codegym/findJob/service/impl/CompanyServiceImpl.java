package com.codegym.findJob.service.impl;

import com.codegym.findJob.dto.request.SignInFormUser;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.repository.ICompanyRepo;
import com.codegym.findJob.security.jwt.JwtProvider;
import com.codegym.findJob.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    ICompanyRepo repo;

    PasswordEncoder encoder;

    JwtProvider token;

    @PersistenceContext
    EntityManager em;

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
    public Optional<Company> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public String login(SignInFormUser signInForm) {
        Optional<Company> cpn = repo.findByEmail(signInForm.getEmail());

        if (cpn.isEmpty()) {
            logger.error("Company not exist");
        } else if (encoder.matches(signInForm.getPassword(), cpn.get().getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("USER_ID", null);
            claims.put("COMPANY_ID", cpn.get().getId());
            claims.put("isCompany", true);
            claims.put("status", cpn.get().isStatus());

            return token.createToken("COMPANY_TOKEN", claims);
        } else {
            logger.error("Email or password incorrect");
        }
        return null;
    }

    @Override
    public List<Company> findAll() {
        return repo.findAll();
    }

    @Override
    public void saveEdit(Company company) {
        if (company.getPassword() == null){
            company.setPassword(findById(company.getId()).get().getPassword());
        }
        save(company);
    }





}
