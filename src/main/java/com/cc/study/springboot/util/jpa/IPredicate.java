package com.cc.study.springboot.util.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author chenc
 * @date 2021/06/01
 **/
public interface IPredicate {
    enum Operator {
        EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR
    }

    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                          CriteriaBuilder builder);
}
