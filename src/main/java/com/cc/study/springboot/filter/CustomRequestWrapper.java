package com.cc.study.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * HttpservletRequest包装器，配合filter进行使用
 * 可以将request请求中的参数等信息进行修改
 * <p>
 * 一个简单的装饰器模式，目的是进一步的对原有的属性或是操作进行扩展
 *
 * @author chenc
 * @date 2019/11/21
 **/
@Slf4j
public class CustomRequestWrapper extends HttpServletRequestWrapper {
    private final HttpServletRequest request;
    private final Map<String, String[]> newParameterMap = new HashMap<>();

    public CustomRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String[] value = newParameterMap.get(name);
        if (value.length != 0) {
            return value[0];
        }
        return "";
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Enumeration<String> parameterNames = getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String elementName = parameterNames.nextElement();
            log.info("request paramterName: {}", elementName);

        }
        newParameterMap.put("id", new String[]{"a1"});
        newParameterMap.put("name", new String[]{"b1"});
        newParameterMap.put("age", new String[]{"22"});

        return newParameterMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Set<String> keySet = newParameterMap.keySet();
        return Collections.enumeration(keySet);
    }

    /**
     * 最终对request的取值起作用的是该方法
     *
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        // TODO
        getParameterMap();
        return newParameterMap.get(name);
    }
}
