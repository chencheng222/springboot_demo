package com.cc.study.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalField;

/**
 * @author chenc
 * @date 2019/11/18
 **/
public class Test {

    @Autowired
    CacheManager cacheManager;

    public static void main(String[] args) {
        Instant now = Instant.now();
        long epochSecond = now.getEpochSecond();

        System.out.println("epochSecond \t" + epochSecond);
        System.out.println("System.currentTimeMillis()\t" + System.currentTimeMillis());

        LocalDate localDateNow = LocalDate.now();

        System.out.println("dev branch");
        System.out.println("test stash");
    }


}
