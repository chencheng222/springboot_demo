package com.cc.study.springboot.controller;

import com.cc.study.springboot.common.ResponseResult;
import com.cc.study.springboot.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenc
 * @date 2020/11/11
 **/
public class BaseController<T, ID extends Serializable, S extends IBaseService<T, ID>> {

    @Autowired
    private S service;

    @GetMapping("/basicFindAll")
    public ResponseResult findAll() {
        List<T> all = service.findAll();

        return ResponseResult.builder().success().data(all);
    }

    @GetMapping("/basicGetOne")
    public ResponseResult getOne(ID id) {
        T one = service.getOne(id);

        return ResponseResult.builder().success().data(one);
    }

    @DeleteMapping("/basicDeleteById")
    public ResponseResult deleteById(ID id) {
        service.deleteById(id);

        return ResponseResult.builder().success();
    }

    @PostMapping("/basicSave")
    public ResponseResult save(T t) {
        service.save(t);

        return ResponseResult.builder().success();
    }
}
