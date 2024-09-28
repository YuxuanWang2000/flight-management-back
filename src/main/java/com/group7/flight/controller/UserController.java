package com.group7.flight.controller;


import com.group7.flight.dto.RegisterDto;
import com.group7.flight.response.ResponseResult;
import com.group7.flight.service.UserService;
import com.group7.flight.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseResult<String>  register(@RequestBody RegisterDto registerDto){
        if (userService.register(registerDto)) {
            return ResponseResult.success("Register success!");
        }
        return ResponseResult.fail("register failed!");
    }

    @GetMapping("/checkToken")
    public ResponseResult<Boolean> checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info(token);

        JwtUtil.isTokenExpired(token);
        return ResponseResult.success(true);

    }

}
