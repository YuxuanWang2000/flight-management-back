package com.group7.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class User {

    public User(String username, String password, String email, String tel, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.name = name;
    }

    private long id;
    private String username;
    private String password;
    private String email;
    private String tel;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
