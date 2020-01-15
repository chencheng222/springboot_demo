package com.cc.study.springboot.config;

import com.cc.study.springboot.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author chenc
 */
@Configuration
public class CustomWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private CustomInterceptor customInterceptor;

    /**
     * 解决跨域问题
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    /**
     * 添加拦截器
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor).addPathPatterns("/**");
    }

    /**
     * 默认静态资源处理器
     **/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    /**
     * 静态资源处理
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    /**
     * 视图跳转控制器
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * 这里配置视图解析器
     **/
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 注册SpringBoot对于response的数据转换
        converters.add(new ConsumeHttpMessageConverter());
    }
}
