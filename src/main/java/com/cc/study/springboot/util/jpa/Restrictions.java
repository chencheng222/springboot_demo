package com.cc.study.springboot.util.jpa;

import org.springframework.util.StringUtils;

/**
 * @author chenc
 * @date 2021/06/01
 **/
public class Restrictions {

    /**
     * 等于
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate eq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate ne(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate like(String fieldName, String value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.LIKE);
    }

    /**
     * 大于
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate gt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.GT);
    }

    /**
     * 小于
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate lt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.LT);
    }

    /**
     * 大于等于
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate lte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.GTE);
    }

    /**
     * 小于等于
     *
     * @param fieldName  字段名
     * @param value      值
     * @param ignoreNull 是否忽略null
     * @return SimplePredicate
     */
    public static SimplePredicate gte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimplePredicate(fieldName, value, IPredicate.Operator.LTE);
    }

    /**
     * 或者
     *
     * @param predicates 表达式
     * @return
     */
    public static LogicalPredicate or(IPredicate... predicates) {
        return new LogicalPredicate(predicates, IPredicate.Operator.OR);
    }
}
