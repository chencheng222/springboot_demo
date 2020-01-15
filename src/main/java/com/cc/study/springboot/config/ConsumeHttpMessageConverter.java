package com.cc.study.springboot.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * response数据加密
 *
 * @author chenc
 * @date 2019/11/07
 **/
@Slf4j
public class ConsumeHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final String BODY_KEY = "body";

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //使用Jackson的ObjectMapper将Java对象转换成Json String
        ObjectMapper mapper = new ObjectMapper();
        String responseData = mapper.writeValueAsString(object);

        // 防止二次加密
        if (JSONObject.parseObject(responseData).containsKey(BODY_KEY)) {
            outputMessage.getBody().write(responseData.getBytes());
            return;
        }

        log.info("ConsumeHttpMessageConverter->responseData加密前:" + responseData);
        //加密
        // SM4Utils sm4Utils = new SM4Utils();
        String ecbData = "";
        log.info("ConsumeHttpMessageConverter->responseData加密后:" + ecbData);

        //输出
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(BODY_KEY, ecbData);
        log.info("ConsumeHttpMessageConverter->response:" + jsonObject);

        outputMessage.getBody().write(jsonObject.toJSONString().getBytes());
    }

}
