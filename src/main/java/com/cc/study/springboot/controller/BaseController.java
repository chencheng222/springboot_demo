package com.cc.study.springboot.controller;

import com.cc.study.springboot.common.ResponseResult;
import com.cc.study.springboot.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    public ResponseResult findAll(T t) {
        List<T> all = service.findAll(t);

        return ResponseResult.builder().success().data(all);
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

    @PutMapping("/basicUpdate")
    public ResponseResult update(T t) {
        boolean update = service.update(t);
        if (!update) {
            return ResponseResult.builder().failure();
        }

        return ResponseResult.builder().success();
    }
}
