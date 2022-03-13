package com.codegym.findJob.dto.request;

public class SignInFormUser {
    private String email;
    private String password;

    public SignInFormUser() {
    }

    public SignInFormUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
