package com.cc.study.springboot.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenc
 * @date 2020/11/11
 **/
public interface IBaseService<T, ID extends Serializable> {

    List<T> findAll(T t);

    boolean save(T t);

    boolean update(T t);

    boolean deleteById(ID id);
}
