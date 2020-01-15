package com.cc.study.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenc
 * @date 2019/11/21
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/get")
    public String get(String id, String name, Integer age) throws Exception {
        log.info("get -> id: {}", id);
        log.info("get -> name: {}", name);
        log.info("get -> age: {}", age);

        return "TestController -> get";
    }

    @GetMapping("/post")
    public String post(String id, String name) throws Exception {
        log.info("post -> id: {}", id);
        log.info("post -> name: {}", name);

        return "TestController -> post";
    }
}
