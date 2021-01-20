package com.cc.study.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.cc.study.springboot.common.CommonPostVo;
import com.cc.study.springboot.common.ResponseResult;
import com.cc.study.springboot.util.SM4Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Map;

/**
 * 总入口
 *
 * @author chenc
 * @date 2019/12/03
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class DispatchController {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 服务器时间，用来判断会话重放
     */
    private static final String SERVER_TIME = "serverTime";

    @GetMapping("/getfunc")
    public String getfunc(String body) throws UnsupportedEncodingException, JsonProcessingException {
        log.info("getfunc start.");
        log.info("getfunc param：{}", body);

        Map<String, Object> paramMap = null;
        if (StrUtil.isEmpty(body) || (paramMap = this.sm4decrypt(URLDecoder.decode(body, "UTF-8"))) == null) {
            return objectMapper.writeValueAsString(ResponseResult.builder().failure());
        }

        final String[] getUrl = {"http://localhost:8888/zzbd_ws" + paramMap.get("api") + "?"};
        paramMap.forEach((k, v) -> {
            getUrl[0] = getUrl[0] + k + "=" + v + "&";
        });

        log.info(getUrl[0]);
//        return HttpRequest.get(getUrl[0]).execute().body();
        return objectMapper.writeValueAsString(ResponseResult.builder().failure().message("会话重放"));
    }

    @PostMapping("/postfunc")
    public String postfunc(@RequestBody CommonPostVo postVo) throws JsonProcessingException {
        log.info("postfunc start.");
        String body = postVo.getBody();
        log.info("postfunc param：{}", body);

        Map<String, Object> paramMap = null;
        if (StrUtil.isEmpty(body) || (paramMap = this.sm4decrypt(body)) == null) {
            return objectMapper.writeValueAsString(ResponseResult.builder().failure());
        }

        final String postUrl = "http://localhost:8888/zzbd_ws" + paramMap.get("api");

        return HttpRequest.post(postUrl).form(paramMap).execute().body();
    }

    /**
     * sm4解密
     *
     * @param content 待解密内容
     * @return
     */
    private Map<String, Object> sm4decrypt(String content) {
        String jsonStr = SM4Utils.decryptData_ECB(content);

        try {
            Map<String, Object> paramMap = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
            });
            log.info("sm4decrypt：{}", paramMap.toString());

            return paramMap;
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    /**
     * 判断是否会话重放
     *
     * @param jsonObject 请求时间
     * @return true:会话重放
     */
    private boolean isResession(Map<String, Object> jsonObject) {
        if (!jsonObject.containsKey(SERVER_TIME)) {
            return false;
        }
        Object serverTime = jsonObject.get(SERVER_TIME);

        String requestTime = "";
        if (StringUtils.isEmpty(requestTime)) {
            return false;
        }
        // 服务器当前时间
        long currentServerTime = System.currentTimeMillis();

        Calendar requestTimeCal = Calendar.getInstance();
        requestTimeCal.setTimeInMillis(Long.parseLong(requestTime));

        requestTimeCal.add(Calendar.SECOND, 10);
        long requestTimeCalTimeInMillis = requestTimeCal.getTimeInMillis();
        if (currentServerTime > requestTimeCalTimeInMillis) {
            return true;
        }
        return false;
    }

}
