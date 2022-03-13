package com.codegym.findJob.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CompanyShorted {
    @Id
    Long company_id;
    int countid;

}
