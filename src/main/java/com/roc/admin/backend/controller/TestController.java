package com.roc.admin.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t1")
    public String test(@RequestParam String hh) {
        String path = System.getProperty("Dtest");
        System.out.println(path);
        String path2 = System.getProperty("Dtest2");
        System.out.println(path2);
        return "Hello World";

    }

    @GetMapping(value = "/user")
    public String getUserList() {

        return "user";
    }
}
