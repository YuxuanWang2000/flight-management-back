package com.group7.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String tel;
    private String name;
    private String nickname;
    private Date createdDate;
    private Date updatedDate;

    public User(String username, String password, String nickname, String email, String tel, String name) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.tel = tel;
        this.name = name;
    }

    public User(String username, String email, String tel, String name, String nickname) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.name = name;
        this.nickname = nickname;
    }

}
