package com.cc.study.springboot.common;

import lombok.Data;

/**
 * @author chenc
 * @date 2020/09/17
 **/
@Data
public class ResponseResult {

    private int code;

    private String message;

    private Object data;

    public static ResponseResult builder() {
        return new ResponseResult();
    }

    public ResponseResult code(int code) {
        this.code = code;
        return this;
    }

    public ResponseResult message(String message) {
        this.message = message;
        return this;
    }

    public ResponseResult data(Object data) {
        this.data = data;
        return this;
    }

    public ResponseResult success() {
        this.code = 1;
        this.message = "success";
        return this;
    }

    public ResponseResult failure() {
        this.code = -1;
        this.message = "failure";
        return this;
    }

    public ResponseResult empty() {
        this.code = 0;
        this.message = "结果为空";
        return this;
    }
}
