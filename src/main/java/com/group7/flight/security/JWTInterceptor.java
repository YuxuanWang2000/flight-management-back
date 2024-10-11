package com.group7.flight.security;


import com.group7.flight.util.JwtUtil;
import com.mysql.cj.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        log.info("prehandle: " + token);
        if (!StringUtils.isNullOrEmpty(token)) {
            if (JwtUtil.isTokenExpired(token)) {
                throw new SignatureException("token is expired, please login again.");
            }

            // Verify the Token and extract the user name
            String username = JwtUtil.getUsernameFromToken(token);
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            // Create an Authentication object
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            // Set the authentication information to the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("authentication success!");
            return true;
        }
        throw new SignatureException("no token, please login.");
    }
}
