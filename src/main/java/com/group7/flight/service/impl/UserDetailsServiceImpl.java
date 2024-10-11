package com.group7.flight.service.impl;

import com.group7.flight.entity.SecurityUser;
import com.group7.flight.entity.User;
import com.group7.flight.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.valueOf(userMapper));
        User user = userMapper.getUserByUsername(username);
        log.info("userDetailsServiceImpl: " + username +  ", " + user.getPassword());
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        boolean matches = bCryptPasswordEncoder.matches("414670", user.getPassword());
//        log.info("matches: {}", matches);
        if (user == null) {
            throw new UsernameNotFoundException("username not exists");
        }
        return new SecurityUser(username, user.getPassword());
    }
}
