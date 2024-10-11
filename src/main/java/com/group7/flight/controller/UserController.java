package com.group7.flight.controller;


import com.group7.flight.dto.RegisterDTO;
import com.group7.flight.dto.UserInfoDTO;
import com.group7.flight.response.ResponseResult;
import com.group7.flight.service.UserService;
import com.group7.flight.util.JwtUtil;
import com.group7.flight.vo.UserVO;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    // register new account
    @PostMapping("/register")
    public ResponseResult<String>  register(@RequestBody RegisterDTO registerDto){
        log.info(registerDto.toString());
        if (userService.register(registerDto)) {
            return ResponseResult.success("Register success!");
        }
        return ResponseResult.fail("register failed!");
    }

    // validate token
    @GetMapping("/checkToken")
    public ResponseResult<Boolean> checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info(token);

        JwtUtil.isTokenExpired(token);
        return ResponseResult.success(true);
    }

    // get user info
    @GetMapping("/userInfo")
    public ResponseResult<UserVO> getUserInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        String username = JwtUtil.getUsernameFromToken(token);

        return ResponseResult.success(userService.getUserInfo(username));
    }

    // update user info
    @PostMapping("/userInfo")
    public ResponseResult<Boolean> updateUserInfo (HttpServletRequest request, @RequestBody UserInfoDTO userInfoDTO) {
        String token = request.getHeader("token");
        String username = JwtUtil.getUsernameFromToken(token);
        log.info(userInfoDTO.toString());

        return ResponseResult.success(userService.updateUserInfo(username, userInfoDTO) == 1);
    }
}
