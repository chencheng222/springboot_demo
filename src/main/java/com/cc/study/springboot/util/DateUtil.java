package com.cc.study.springboot.util;

import org.joda.time.DateTimeUtils;
import org.joda.time.LocalTime;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author chenc
 * @date 2019/11/18
 **/
public class DateUtil {

    public static void main(String[] args) {
    }

    private DateUtil() {
    }

    /**
     * 当前系统时间（毫秒）
     *
     * @return 当前系统时间
     */
    public static String getUnixTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 将当前时间格式化
     *
     * @param pattern 格式
     * @return 格式化后的当前时间
     */
    public static String getNowTimeOfPattern(String pattern) {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern(pattern);

        return dateToStrFormatter.format(localDateTime1);
    }
}
