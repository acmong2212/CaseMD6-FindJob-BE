package com.codegym.findJob.email;

import com.codegym.findJob.model.Users;
import org.springframework.http.ResponseEntity;

public interface IRegistrationService {
    ResponseEntity<?> register(Users users);
}
