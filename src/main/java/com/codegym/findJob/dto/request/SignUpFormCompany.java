package com.codegym.findJob.dto.request;

import com.codegym.findJob.model.RoleName;
import lombok.Data;

import java.util.Set;


@Data
public class SignUpFormCompany {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String description;
    private String avatar;
    private Set<String> roles;
}
