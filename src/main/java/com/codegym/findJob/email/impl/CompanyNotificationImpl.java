package com.codegym.findJob.email.impl;

import com.codegym.findJob.dto.response.ResponseMessage;
import com.codegym.findJob.email.ICompanyNotificationService;
import com.codegym.findJob.model.Company;
import com.codegym.findJob.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyNotificationImpl implements ICompanyNotificationService {

    private ICompanyService companyService;

    private EmailServiceImpl sendEmailService;

    @Override
    public ResponseEntity<?> notificationConfirm(Company company) {
        companyService.save(company);
        String emailTo = company.getEmail();
        String body = "Bạn đã được xác nhận để truy cập vào Find Job! Đăng bài tuyển dụng thôiii";
        String title = "Thông báo về việc xác nhận tài khoản";
        sendEmailService.send(emailTo,body,title);
        return new ResponseEntity<>(new ResponseMessage("confirmed"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> notificationUnConfirm(Company company) {
        companyService.save(company);
        String emailTo = company.getEmail();
        String body = "Bạn đã bị khoá tài khoản do vi phạm tiêu chuẩn cộng đồng của Find Job. Nếu có thắc mắc, hãy liên hệ với chúng tôi.";
        String title = "Thông báo về việc khoá tài khoản của bạn";
        sendEmailService.send(emailTo,body,title);
        return new ResponseEntity<>(new ResponseMessage("unConfirmed"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> notificationUserApply(Company company) {
        String emailTo = company.getEmail();
        String body = "Hi " + company.getName() + ", ai đó đã ứng tuyển vào bài post của bạn. Kiểm tra nhé!";
        String title = "Thông báo chút nè :3";
        sendEmailService.send(emailTo,body,title);
        return new ResponseEntity<>(new ResponseMessage("Applied"), HttpStatus.OK);
    }
}
