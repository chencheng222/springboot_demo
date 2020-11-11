package com.cc.study.springboot.service;

import com.cc.study.springboot.dao.BaseRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenc
 * @date 2020/11/11
 **/
public interface IBaseService<T, ID extends Serializable> {

    List<T> findAll();

    T getOne(ID var1);

    boolean save(T t);

    boolean deleteById(ID id);
}
