package com.roc.admin.backend.filter;

import com.roc.admin.backend.dao.entity.RbacUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!request.getRequestURI().equals("/tomin/login/sessionDemo")) {
            RbacUser user = (RbacUser) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("http://localhost:9001/tomin/login/sessionDemo");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

}
