package com.codegym.findJob.dao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginReq {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,30}$",message = "Username must be characters and number only")
    private String username;

    @NotBlank
    private String password;
}
