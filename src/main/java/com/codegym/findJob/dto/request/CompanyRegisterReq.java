package com.codegym.findJob.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CompanyRegisterReq {

    private String name;
    @NotBlank
    private String password;
    @Email
    private String email;
    private String description;
    private String avatar;
}
