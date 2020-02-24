package com.cc.study.springboot;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalField;

/**
 * @author chenc
 * @date 2019/11/18
 **/
public class Test {

    public static void main(String[] args) {
        Instant now = Instant.now();
        long epochSecond = now.getEpochSecond();

        System.out.println("epochSecond \t" + epochSecond);
        System.out.println("System.currentTimeMillis()\t" + System.currentTimeMillis());

        LocalDate localDateNow = LocalDate.now();

        System.out.println("dev branch");
    }
}
