package com.cc.study.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chenc
 * @date 2019/11/21
 **/
@WebFilter(urlPatterns = "/*")
@Slf4j
public class WrapperFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter start.");

        CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(customRequestWrapper, servletResponse);

        log.info("doFilter end.");
    }
}
