package com.group7.flight.security;


import com.group7.flight.util.JwtUtil;
import com.mysql.cj.util.StringUtils;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (StringUtils.isNullOrEmpty(token)) {
            if (JwtUtil.isTokenExpired(token)) {
                throw new SignatureException("token is expired, please login again.");
            }
            throw new SignatureException("no token, please login.");
        }
        return true;
    }
}
