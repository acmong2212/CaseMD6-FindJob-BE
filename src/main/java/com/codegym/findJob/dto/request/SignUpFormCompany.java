package com.codegym.findJob.dto.request;

import lombok.Data;

@Data
public class SignUpFormCompany {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String description;
    private String avatar;
    private String address;
    private Long numberOfEmployees;
    private String branch;
    private String mapLink;
    private String website;
    private String phoneNumber;
}
