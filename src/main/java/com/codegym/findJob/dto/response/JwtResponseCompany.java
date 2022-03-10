package com.codegym.findJob.dto.response;

import com.codegym.findJob.model.Company;

public class JwtResponseCompany {
    private String token;
    private String type = "Bearer";
    private Company company;

    public JwtResponseCompany(String token, Company company) {
        this.token = token;
        this.company = company;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
