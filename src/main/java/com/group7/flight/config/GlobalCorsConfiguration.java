package com.group7.flight.config;

import com.group7.flight.security.JWTInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class GlobalCorsConfiguration implements WebMvcConfigurer {
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Bean
    public CorsFilter corsFilter() {
        // Add CORS configuration information
        CorsConfiguration config = new CorsConfiguration();
        // Which raw domains are allowed
        config.addAllowedOrigin(CorsConfiguration.ALL);
        // Whether to send cookies
        config.setAllowCredentials(true);
        // Which request modes are allowed
        config.addAllowedMethod(CorsConfiguration.ALL);
        // Which request headers are allowed
        config.addAllowedHeader(CorsConfiguration.ALL);
        // Adding a mapping path
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);

        log.info("CORS Filter is being registered.");
        // Return the new CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor)
//                .excludePathPatterns("/", "/index", "/user/login", "/user/register", "/user/checkToken",
//                        "/favicon.ico", "/static/**", "/js/**", "/css/**", "/img/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}