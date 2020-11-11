package com.cc.study.springboot.controller;

import cn.hutool.core.util.IdUtil;
import com.cc.study.springboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenc
 * @date 2019/11/21
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/get")
    public Object get(String id) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object user = valueOperations.get(id);

        return user;
    }

    @PostMapping("/post")
    public String post(User user) {
        ValueOperations valueOperations = redisTemplate.opsForValue();

        String uuid = IdUtil.fastSimpleUUID();
        user.setId(uuid);

        valueOperations.set(uuid, user);

        return uuid;
    }
}
