package com.group7.flight.dto;


import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String tel;
    private String name;

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
