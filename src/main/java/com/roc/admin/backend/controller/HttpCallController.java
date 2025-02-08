package com.roc.admin.backend.controller;

import com.roc.admin.backend.constant.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/http")
public class HttpCallController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/restTemplate")
    public ResponseData<Object> test1() {
        String fooResourceUrl = "http://baidu.com";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        return ResponseData.success(response);

    }
}
