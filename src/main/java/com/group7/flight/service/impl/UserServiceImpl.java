package com.group7.flight.service.impl;

import com.group7.flight.dto.RegisterDto;
import com.group7.flight.entity.User;
import com.group7.flight.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    public boolean register(RegisterDto registerDto) {
//        String salt = Md5Util.generateMd5Salt();
//        String encodedPassword = Md5Util.generateSaltPassword(registerDto.getPassword(), salt);
        String encodedPassword = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        int num = userMapper.insertUser(
                new User(registerDto.getUsername(), encodedPassword,
                        registerDto.getEmail(), registerDto.getTel(), registerDto.getName()));
        if (num == 1) {
            return true;
        }
        return false;
    }
}