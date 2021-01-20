package com.cc.study.springboot.config;

import com.cc.study.springboot.util.SM4Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

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
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException {
        //使用Jackson的ObjectMapper将Java对象转换成Json String
        ObjectMapper mapper = new ObjectMapper();
        String responseData = "";

        if (object instanceof String) {
            String result = (String) object;
            Map<String, Object> res = mapper.readValue(result, new TypeReference<Map<String, Object>>() {
            });
            // 防止二次加密
            if (res.containsKey(BODY_KEY)) {
                outputMessage.getBody().write(result.getBytes());
                return;
            }
            responseData = result;
        } else {
            responseData = mapper.writeValueAsString(object);
        }

        log.info("ConsumeHttpMessageConverter->responseData加密前:" + responseData);
        //加密
        String ecbData = SM4Utils.encryptData_ECB(responseData);

        //输出
        ObjectNode jsonNodes = mapper.createObjectNode();
        jsonNodes.put(BODY_KEY, ecbData);
        log.info("ConsumeHttpMessageConverter->response:" + jsonNodes.toString());

        outputMessage.getBody().write(jsonNodes.toString().getBytes());
    }

}
