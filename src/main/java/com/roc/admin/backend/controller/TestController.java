package com.roc.admin.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private ObjectMapper mapper;

    @GetMapping("/t1")
    public String test(@RequestBody String hh) throws JsonProcessingException {
        String path = System.getProperty("Dtest");
        System.out.println(path);
        String path2 = System.getProperty("Dtest2");
        System.out.println(path2);
        Map<String, String> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("msg", "success");
        return mapper.writeValueAsString(map);

    }

    @GetMapping("/t2")
    public String test(
            @RequestParam String h1,
            @RequestBody String h2
    ) throws JsonProcessingException {

        Map<String, String> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("msg", "success");
        return mapper.writeValueAsString(map);

    }

    @GetMapping(value = "/user")
    public String getUserList() {

        return "user";
    }
}
