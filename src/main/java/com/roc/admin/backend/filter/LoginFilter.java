package com.roc.admin.backend.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginFilter implements Filter {
    private String filterName = "LoginFilter";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoginFilter doFilter start");
        // 打印请求报文
        System.out.println("Request Body: " + getRequestBody((HttpServletRequest) servletRequest));
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("LoginFilter doFilter end");
    }

    // 获取请求报文体
    private String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
