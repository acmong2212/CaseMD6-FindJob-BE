package com.codegym.findJob.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpFormUser {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String avatar;
    private Set<String> roles;
}
