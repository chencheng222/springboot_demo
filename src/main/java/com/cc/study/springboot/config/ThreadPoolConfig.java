package com.cc.study.springboot.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 *
 * @author chenc
 * @date 2020/08/04
 **/
@Slf4j
@Configuration
public class ThreadPoolConfig {

    /**
     * 定长线程池
     *
     * @return @link{java.util.concurrent.ThreadPoolExecutor}
     */
    @Bean(name = "fixedThreadPool")
    public ThreadPoolExecutor fixedThreadPool() {
        ThreadFactory namedThreadFactory = ThreadFactoryBuilder.create().setNamePrefix("myFixed-pool-").build();

        int processors = 5;

        return new ThreadPoolExecutor(processors, processors + 1, 10L, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(5)
                , namedThreadFactory
                , new ThreadPoolExecutor.AbortPolicy());
    }
}
