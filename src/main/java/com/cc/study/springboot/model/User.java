package com.cc.study.springboot.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenc
 * @date 2020/06/17
 **/
@Data
public class User implements Serializable {

    private String id;

    private String name;

    private Integer age;

}
