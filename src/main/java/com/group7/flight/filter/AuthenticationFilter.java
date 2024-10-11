package com.group7.flight.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.flight.entity.SecurityUser;
import com.group7.flight.service.UserService;
import com.group7.flight.util.JwtUtil;
import com.group7.flight.vo.LoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("attemptAuthentication");
        String username = "";
        String password = "";

        StringBuilder jsonBody = new StringBuilder();

        BufferedReader reader = null;
        try {
            reader = request.getReader();

            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            // Check whether the JSON request body is empty
            if (jsonBody.length() == 0) {
                log.error("jsonBody 长度为 0");
                throw new AuthenticationException("Request body is empty") {};
            }

            // Assume that the request body is a string in JSON format, which is parsed by ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> body = mapper.readValue(jsonBody.toString(), Map.class);

            username = body.get("username");
            password = body.get("password");
            log.info("authentication: " + username + ", " + password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.info("username: " + username + ", password: " + password);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        String token = JwtUtil.createToken(user.getUsername());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("token", token);
        log.info(token);
        response.getWriter().write(token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error(failed.getMessage());
        // Create the returned Map object
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("code", 500);
        responseMap.put("message", "authentication failed, reason: " + failed.getMessage());

        // Convert the Map to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseMap);

        response.getWriter().write(jsonResponse);
    }
}
