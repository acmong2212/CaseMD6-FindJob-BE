package com.codegym.findJob.email.impl;

import com.codegym.findJob.email.IEmailSender;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements IEmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(String to, String body, String topic) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("findjobcasemd6@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }
}
