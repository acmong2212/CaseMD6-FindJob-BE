package com.codegym.findJob.dto.request;

import lombok.Data;

@Data
public class SearchForm {
    private String _title;
    private String _jobLocation;
    private Long _idField;
    private Double _minSalary;
    private Double _maxSalary;


}
