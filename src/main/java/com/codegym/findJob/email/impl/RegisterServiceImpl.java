package com.codegym.findJob.email.impl;

import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.email.IRegistrationService;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.ICompanyService;
import com.codegym.findJob.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements IRegistrationService {

    private IUserService userService;

    private ICompanyService companyService;

    private EmailServiceImpl sendEmailService;

    @Override
    public ResponseEntity<?> register(Users users) {
        userService.save(users);
        String emailTo = users.getEmail();
        String body = "Successful account registration, welcome to Find Job";
        String title = "Hi, i'm Find Job";
        sendEmailService.send(emailTo,body,title);
        return new ResponseEntity<>(new ResponseMessage("registered"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerCompany(Company company) {
        companyService.save(company);
        String emailTo = "depzai02x@gmail.com";
        String body = "Có 1 doanh nghiệp muốn đăng ký, hãy kiểm tra doanh nghiệp có tài khoản gmail là " + company.getEmail() + " trước khi xác nhận nhé!";
        String title = "Có yêu cầu trở thành doanh nghiệp nè!";
        sendEmailService.send(emailTo,body,title);
        return new ResponseEntity<>(new ResponseMessage("registered"), HttpStatus.OK);
    }
}
