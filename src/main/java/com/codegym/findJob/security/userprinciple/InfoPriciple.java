package com.codegym.findJob.security.userprinciple;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoPriciple {
    private Long userId;
    private Long companyId;
    private String name;
    private String email;
}
