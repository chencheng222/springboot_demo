package com.cc.study.springboot.config;

import com.cc.study.springboot.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author chenc
 */
@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    @Autowired
    private CustomInterceptor customInterceptor;

    /**
     * 解决跨域问题
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    /**
     * 添加拦截器
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(customInterceptor).addPathPatterns("/**");
    }

    /**
     * 默认静态资源处理器
     **/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    /**
     * 资源路径的配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
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
//        converters.add(new ConsumeHttpMessageConverter());
    }

    /**
     * 配置允许put、delete请求方法
     *
     * @return
     */
    @Bean
    public FormContentFilter httpPutFormContentFilter() {
        return new FormContentFilter();
    }
}
