package com.group7.flight.dto;


import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private String tel;
    private String name;
}
