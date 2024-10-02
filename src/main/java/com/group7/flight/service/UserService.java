package com.group7.flight.service;


import com.group7.flight.dto.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public boolean register(RegisterDto registerDto);
}
