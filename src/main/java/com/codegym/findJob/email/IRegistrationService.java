package com.codegym.findJob.email;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
import org.springframework.http.ResponseEntity;

public interface IRegistrationService {
    ResponseEntity<?> register(Users users);
    ResponseEntity<?> registerCompany(Company company);
}
