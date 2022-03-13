package com.codegym.findJob.dto.response;


import com.codegym.findJob.model.Users;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Users users;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
        this.users = users;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
