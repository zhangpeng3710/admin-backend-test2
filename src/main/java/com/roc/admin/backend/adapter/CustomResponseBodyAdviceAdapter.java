package com.roc.admin.backend.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roc.admin.backend.constant.ResponseData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/5/18
 */
@Slf4j
@RestControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {

        if (body instanceof String) {
            return mapper.writeValueAsString(ResponseData.success(body));
        }
        if (body instanceof ResponseData) {
            return body;
        }
        return ResponseData.fail(body);

    }


}
