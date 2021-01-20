package com.cc.study.springboot.controller;

import com.cc.study.springboot.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenc
 * @date 2019/11/21
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/dailyPaper/showContent")
    public ResponseResult get(String dailyPaperId) {
        log.info("dailyPaperId: {}", dailyPaperId);

        return ResponseResult.builder().success();
    }

    @PostMapping("/sms/sendMsg")
    public ResponseResult post(String sentTime, String sentText, String toUserIds) {
        log.info("sentTime:{}, sentText:{}, toUserIds:{}", sentTime, sentText, toUserIds);

        return ResponseResult.builder().success();
    }
}
