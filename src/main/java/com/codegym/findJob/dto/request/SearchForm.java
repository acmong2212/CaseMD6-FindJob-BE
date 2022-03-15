package com.codegym.findJob.dto.request;

import lombok.Data;

@Data
public class SearchForm {
    private String title;
    private String companyName;
    private String address;
    private Long idField;
    private Double minSalary;
    private Double maxSalary;
}
