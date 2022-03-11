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
}
