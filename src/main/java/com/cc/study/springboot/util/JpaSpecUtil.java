package com.cc.study.springboot.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenc
 * @date 2020/05/09
 **/
@Slf4j
public class JpaSpecUtil {

    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 实例化
     *
     * @return JpaSpecUtil
     */
    public static JpaSpecUtil getInstance() {
        return new JpaSpecUtil();
    }

    /**
     * PUT接口
     * 遍历对象属性进行修改对象参数。
     *
     * @param tParam  前端传过来的参数对象。
     * @param tSearch 从数据库中查出来的参数对象。
     * @return 更新对象
     */
    public <T> T convertUpdateObj(T tParam, T tSearch) {
        try {

            Field[] fields = tSearch.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(tParam);
                if (value != null) {
                    field.set(tSearch, value);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return tSearch;
    }

    /**
     * 通过反射对象，实现生成Jpa高级查询条件
     *
     * @param t 反射对象
     * @param ignoreFields 需要忽略的查询字段
     * @param jpaSearchModeBeans 字段查询的方式
     * @param <T> 对象
     * @return JPA Specification
     */
    public <T> Specification<T> specificateObject(T t, List<String> ignoreFields, JpaSearchModeBean... jpaSearchModeBeans) {
        Specification<T> spec = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            setCondition2PredicateList(t, predicateList, root, cb, ignoreFields, jpaSearchModeBeans);

            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };

        return spec;
    }

    /**
     * 追加查询条件到查询列表
     *
     * @param t 查询对象
     * @param predicateList 查询条件list
     * @param root
     * @param cb
     * @param ignoreFields 忽略字段
     * @param jpaSearchModeBeans 高价查询字段
     * @param <T>
     */
    public <T> void setCondition2PredicateList(T t, List<Predicate> predicateList, Root<T> root, CriteriaBuilder cb, List<String> ignoreFields, JpaSearchModeBean... jpaSearchModeBeans) {
        // 类字段
        Field[] subfields = t.getClass().getDeclaredFields();
        // 父类字段
        Field[] declaredFields = t.getClass().getSuperclass().getDeclaredFields();

        Field[] totalFields = ArrayUtil.addAll(subfields, declaredFields);

        for (int i = 0; i < totalFields.length; i++) {
            Field field = totalFields[i];
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            if (SERIAL_VERSION_UID.equals(fieldName)) {
                continue;
            }
            // 跳过忽略的检索字段
            if (!CollectionUtils.isEmpty(ignoreFields) && ignoreFields.contains(fieldName)) {
                continue;
            }
            try {
                // private修饰属性需要设定
                field.setAccessible(true);
                Object value = field.get(t);


                JpaSearchModeBean modeBean = getPredicateBySearchMode(field, jpaSearchModeBeans);
                if (modeBean == null) {
                    if (value == null || StrUtil.isEmpty(String.valueOf(value))) {
                        continue;
                    }
                    // 默认采用equal
                    predicateList.add(cb.equal(root.get(fieldName).as(fieldType), value));
                    continue;
                }
                // 其他排序方式
                JpaSearchModeBean.SearchMode searchMode = modeBean.getMode();
                if (JpaSearchModeBean.SearchMode.LIKE.equals(searchMode)) {
                    if (value == null || StrUtil.isEmpty(String.valueOf(value))) {
                        continue;
                    }
                    predicateList.add(cb.like(root.get(fieldName).as(String.class), "%" + value + "%"));
                } else if (JpaSearchModeBean.SearchMode.BETWEEN.equals(searchMode)) {
                    predicateList.add(cb.between(root.get(fieldName), modeBean.getBetweenFrom(),
                            modeBean.getBetweenTo()));
                } else if (JpaSearchModeBean.SearchMode.IN.equals(searchMode)) {
                    CriteriaBuilder.In<Object> in = cb.in(root.get(fieldName));
                    List<Object> inValues = modeBean.getInValues();
                    if (!CollectionUtils.isEmpty(inValues)) {
                        inValues.forEach(e -> in.value(e));
                        predicateList.add(in);
                    }
                } else if (JpaSearchModeBean.SearchMode.IS_NULL.equals(searchMode)) {
                    List<String> nullFields = modeBean.getNullFields();
                    if (!CollectionUtils.isEmpty(nullFields)) {
                        for (String nullField : nullFields) {
                            predicateList.add(cb.isNull(root.get(nullField)));
                        }
                    }
                } else if (JpaSearchModeBean.SearchMode.IS_NOT_NULL.equals(searchMode)) {
                    List<String> notNullFields = modeBean.getNotNullFields();
                    if (!CollectionUtils.isEmpty(notNullFields)) {
                        for (String notNullField : notNullFields) {
                            predicateList.add(cb.isNotNull(root.get(notNullField)));
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException();
            }
        }
    }


    /**
     * 检索mode取得
     *
     * @param field 操作字段
     * @param jpaSearchModeBeans 对象字段检索mode数组
     * @return JpaSearchModeBean
     */
    private JpaSearchModeBean getPredicateBySearchMode(Field field, JpaSearchModeBean... jpaSearchModeBeans) {
        if (jpaSearchModeBeans == null || jpaSearchModeBeans.length == 0) {
            return null;
        }

        String fieldName = field.getName();
        for (int i = 0; i < jpaSearchModeBeans.length; i++) {
            JpaSearchModeBean searchModeBean = jpaSearchModeBeans[i];
            if (fieldName.equals(searchModeBean.getFieldName())) {
                return searchModeBean;
            }
        }

        return null;
    }
}
