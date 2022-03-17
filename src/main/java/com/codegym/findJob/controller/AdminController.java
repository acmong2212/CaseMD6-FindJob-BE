package com.codegym.findJob.controller;

import com.codegym.findJob.email.ICompanyNotificationService;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    private ICompanyService companyService;

    private ICompanyNotificationService companyNotificationService;

    // Duyệt doanh nghiệp khi đăng ký
    @PutMapping("/confirmCompany/{idCompany}")
    public ResponseEntity<?> confirmCompany(@PathVariable("idCompany") Long id) {
        Company company = companyService.findById(id).get();
        company.setStatus(true);
        companyNotificationService.notificationConfirm(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    // Block doanh nghiệp
    @PutMapping("/unConfirmCompany/{idCompany}")
    public ResponseEntity<?> unConfirmCompany(@PathVariable("idCompany") Long id) {
        Company company = companyService.findById(id).get();
        company.setStatus(false);
        companyNotificationService.notificationUnConfirm(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
