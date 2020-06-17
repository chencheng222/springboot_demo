package com.cc.study.springboot_demo;

import com.cc.study.springboot.SpringbootDemoApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@WebAppConfiguration
public class SpringbootDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    public void contextLoads() {
    }

    public void redisTest() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisTest", "helloWorld");
    }


}
