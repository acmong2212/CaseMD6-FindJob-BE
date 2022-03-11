package com.codegym.findJob.email;

public interface IEmailSender {
    void send(String to, String body, String topic);
}
