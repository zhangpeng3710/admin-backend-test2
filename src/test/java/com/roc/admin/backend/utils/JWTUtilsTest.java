package com.roc.admin.backend.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Slf4j
class JWTUtilsTest {

    @Autowired
    private HttpServletRequest request;


    @Test
    public void getToken() {
        String token = JWTUtils.getToken("123");
        log.info(token);
        log.info("haha");
    }

    @Test
    public void verifyToken() {
        String userId = JWTUtils.verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0b21pbiIsInVzZUlkIjoiMTIzIiwiaWF0IjoxNzE3MTcxNTQ2LCJleHAiOjE3MTcxNzE1NzZ9.NpGc8kXmjHkrvJmn8v-tbee9LJOgSclChBbHYc3HBao");
        log.info(userId);

    }

}