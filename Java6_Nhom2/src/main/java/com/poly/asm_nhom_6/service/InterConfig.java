package com.poly.asm_nhom_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poly.asm_nhom_6.config.SecurityInterceptorAdmin;

@Configuration
public class InterConfig implements WebMvcConfigurer {

        @Autowired
        AuthInterceptor auth;

        @Autowired
        SecurityInterceptorAdmin admin;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(auth)
                                .addPathPatterns("/admin/**", "/user/cart/**", "/user/like/**", "/staff/**")
                                .excludePathPatterns("/user/login");

        }
}
