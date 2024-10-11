package com.group7.flight.service.impl;

import com.group7.flight.dto.RegisterDTO;
import com.group7.flight.dto.UserInfoDTO;
import com.group7.flight.entity.User;
import com.group7.flight.mapper.UserMapper;
import com.group7.flight.service.UserService;
import com.group7.flight.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public UserVO getUserInfo(String username) {
        User user = userMapper.getUserByUsername(username);
        UserVO userVO = new UserVO(user.getUsername(), user.getNickname(), user.getTel(),
                user.getName(), user.getEmail(), user.getCreatedDate());
        return userVO;
    }

    public boolean register(RegisterDTO registerDto) {
//        String salt = Md5Util.generateMd5Salt();
//        String encodedPassword = Md5Util.generateSaltPassword(registerDto.getPassword(), salt);
        String encodedPassword = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        log.info("encodedPassword: " + encodedPassword);
        int num = userMapper.insertUser(
                new User(registerDto.getUsername(), encodedPassword, registerDto.getNickname(),
                        registerDto.getEmail(), registerDto.getTel(), registerDto.getName()));
        if (num == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int updateUserInfo(String username, UserInfoDTO userInfoDTO) {
        User user = new User(username, userInfoDTO.getEmail(), userInfoDTO.getTelphone(), userInfoDTO.getRealName(), userInfoDTO.getNickname());
        log.info(user.toString());
        return userMapper.updateUser(user);
    }
}