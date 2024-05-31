package com.roc.admin.backend.interceptor;

import com.roc.admin.backend.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            String userEmail = JWTUtils.verifyToken(token);
            if (StringUtils.isNotBlank(userEmail)) {
                request.setAttribute("userEmail", userEmail);
            }
        }
        return true;
    }
}
