package com.cc.study.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author chenc
 * @create 2019-10-25 11:03
 **/
@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";

    /**
     * 全局的Exception捕捉器
     * 若需要对不同的Exception进行分开处理，只需要定义
     * 不同的方法并注解ExceptionHandler所对应的exception类
     *
     * @param e
     * @return 异常结果
     */
    @ExceptionHandler({Exception.class})
    public String excetion(Exception e) {
        log.error(String.format(logExceptionFormat, 500, e.getMessage()));

        return "500";
    }
}
