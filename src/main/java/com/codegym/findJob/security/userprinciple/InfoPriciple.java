package com.codegym.findJob.security.userprinciple;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class InfoPriciple {
    private Long userId;
    private Long companyId;
    private Company company;
    private Users user;
}
