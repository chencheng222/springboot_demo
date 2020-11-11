package com.cc.study.springboot.service.impl;

import com.cc.study.springboot.dao.BaseRepository;
import com.cc.study.springboot.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenc
 * @date 2020/11/11
 **/
public class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements IBaseService<T, ID> {

    @Autowired
    protected R baseRepository;

    @Override
    public List<T> findAll() {
        List<T> all = baseRepository.findAll();

        return all;
    }

    @Override
    public T getOne(ID var1) {
        T one = baseRepository.getOne(var1);

        return one;
    }

    @Override
    public boolean save(T t) {
        baseRepository.save(t);

        return true;
    }

    @Override
    public boolean deleteById(ID id) {
        baseRepository.deleteById(id);

        return true;
    }
}
