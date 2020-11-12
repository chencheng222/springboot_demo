package com.cc.study.springboot.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cc.study.springboot.dao.BaseRepository;
import com.cc.study.springboot.service.IBaseService;
import com.cc.study.springboot.util.JpaSpecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author chenc
 * @date 2020/11/11
 **/
public class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements IBaseService<T, ID> {

    protected JpaSpecUtil jpaSpecUtil = JpaSpecUtil.getInstance();
    @Autowired
    protected R baseRepository;

    @Override
    public List<T> findAll(T t) {
        Specification<T> spec = jpaSpecUtil.specificateObject(t, null, null);

        return baseRepository.findAll(spec);
    }

    @Override
    public boolean save(T t) {
        Class<?> tClass = t.getClass();
        try {
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                boolean present = field.isAnnotationPresent(Id.class);
                if (present) {
                    field.setAccessible(true);
                    field.set(t, IdUtil.fastSimpleUUID());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseRepository.save(t);

        return true;
    }

    @Override
    public boolean update(T t) {
        Object idValue = getIdValue(t);
        if (idValue == null) {
            return false;
        }
        ID id = (ID) idValue;
        T search = baseRepository.findById(id).orElse(null);
        if (search == null) {
            return false;
        }
        T updateObj = jpaSpecUtil.convertUpdateObj(t, search);
        baseRepository.save(updateObj);

        return true;
    }

    @Override
    public boolean deleteById(ID id) {
        baseRepository.deleteById(id);

        return true;
    }

    /**
     * 获取ID值
     *
     * @param t
     * @return
     */
    private Object getIdValue(T t) {
        Class<?> tClass = t.getClass();
        try {
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                boolean present = field.isAnnotationPresent(Id.class);
                if (present) {
                    field.setAccessible(true);
                    return field.get(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
