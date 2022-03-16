package com.codegym.findJob.email;

import com.codegym.findJob.model.Company;
import org.springframework.http.ResponseEntity;

public interface ICompanyNotificationService {
    ResponseEntity<?> notificationConfirm(Company company);
    ResponseEntity<?> notificationUnConfirm(Company company);
}
