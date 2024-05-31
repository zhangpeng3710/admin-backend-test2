package com.roc.admin.backend.config;

import com.roc.admin.backend.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug("LoginInterceptor addInterceptors");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**");
    }
}
