package com.cg.expense.management.domain;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;


    public JwtResponse(String jwt, String username, List<String> roles) {
        this.token = jwt;
        this.username = username;
        this.roles = roles;
    }
}
