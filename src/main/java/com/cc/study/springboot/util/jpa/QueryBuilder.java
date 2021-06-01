package com.cc.study.springboot.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenc
 * @date 2021/06/01
 **/
public class QueryBuilder<T> implements CriteriaQueryBuilder<T> {

    private List<IPredicate> criterions = new ArrayList<>();

    @Override
    public List<Selection<?>> buildSelections(CriteriaBuilder builder, Root<T> root) {
        return null;
    }

    @Override
    public List<Expression<?>> buildGroupBy(Root<T> root) {
        return null;
    }

    @Override
    public List<Tuple> findResult(EntityManager entityManager, Class<T> t) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for(IPredicate c : criterions){
                predicates.add(c.toPredicate(root, query,builder));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }

    /**
     * 增加where子语句
     */
    public void add(IPredicate iPredicate) {
        if (iPredicate != null) {
            criterions.add(iPredicate);
        }
    }
}
