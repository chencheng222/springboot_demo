package com.cc.study.springboot.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * JPA高级检索时mode
 *
 * @author chenc
 * @date 2019/11/04
 **/
@Builder
@Getter
@Setter
public class JpaSearchModeBean {

    /**
     * 检索属性名
     */
    private String fieldName;

    /**
     * 检索方式
     */
    private SearchMode mode;

    /**
     * between起
     */
    private Date betweenFrom;

    /**
     * between止
     */
    private Date betweenTo;

    /**
     * in值
     */
    private List<Object> inValues;

    /**
     * 须为null字段
     */
    private List<String> nullFields;

    /**
     * 不为null字段
     */
    private List<String> notNullFields;

    public enum SearchMode {
        /**
         * like
         */
        LIKE,
        /**
         * between
         */
        BETWEEN,
        /**
         * in
         */
        IN,
        /**
         * null条件
         */
        IS_NULL,
        /**
         * 非null条件
         */
        IS_NOT_NULL
        ;

        SearchMode() {
        }
    }
}
