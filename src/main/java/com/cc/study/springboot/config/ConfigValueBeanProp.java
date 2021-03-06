package com.cc.study.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取一些自定义的配置文件
 *
 * @author chenc
 * @date 2019/12/24
 **/
@Component
@Data
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = "classpath:configValue.properties")
public class ConfigValueBeanProp {

    private String name;
    private Integer age;

}
