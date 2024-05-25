package com.roc.admin.backend.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/5/18
 */
@Slf4j
@RestControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ObjectMapper mapper;

    @Override
    public boolean supports(
            MethodParameter methodParameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object afterBodyRead(
            Object body,
            HttpInputMessage inputMessage,
            MethodParameter parameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        String reqBody;
        if (!(body instanceof String)) {
            reqBody = mapper.writeValueAsString(body);
        } else {
            reqBody = body.toString();
        }
        log.info("body: {}", reqBody);

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
