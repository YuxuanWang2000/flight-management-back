package com.group7.flight.service;


import com.group7.flight.dto.RegisterDTO;
import com.group7.flight.dto.UserInfoDTO;
import com.group7.flight.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserVO getUserInfo(String username);
    public boolean register(RegisterDTO registerDto);
    public int updateUserInfo(String username, UserInfoDTO userInfoDTO);
}
