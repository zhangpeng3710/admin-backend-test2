package com.roc.admin.backend.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class UserOperationRecordFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);

        try {
            ContentCachingRequestWrapper request = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
            ContentCachingResponseWrapper response = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

            Date operationTime = new Date();
            String operationUrl = String.valueOf(request.getRequestURL());
            String userAddress = request.getRemoteAddr();
            String userIp = request.getRemoteHost();
            //remote user
            String remoteUser = request.getRemoteUser();
            //get or post or ...
            String operationType = request.getMethod();
            //context path
            String contextPath = request.getContextPath();
            //character encoding
            String encoding = request.getCharacterEncoding();
            //protocol
            String protocol = request.getProtocol();
            //params in url
            String queryString = request.getQueryString();

            //request body
            ContentCachingRequestWrapper reqTemp = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            byte[] requestBody = Objects.requireNonNull(reqTemp).getContentAsByteArray();
            String body1 = new String(requestBody);
            String reqBody = IOUtils.toString(reqTemp.getInputStream());
            //response body
            ContentCachingResponseWrapper resTemp = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            byte[] responseBody = Objects.requireNonNull(resTemp).getContentAsByteArray();
            String body2 = new String(responseBody);
            String resBody = IOUtils.toString(resTemp.getContentInputStream());

        } catch (Exception e) {
            log.error("User operation record error", e);
        }

    }

}
