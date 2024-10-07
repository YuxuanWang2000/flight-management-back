package com.group7.flight.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserVO {
    private String username;
    private String nickname;
    private String telphone;
    private String realName;
    private String email;
    private Date createdTime;
}
