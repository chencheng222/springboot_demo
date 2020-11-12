package com.cc.study.springboot.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chenc
 * @date 2020/06/17
 **/
@Data
// @DynamicUpdate 使用此注解后，更新时会判断更新字段是否产生变化，若没变化，则该字段不会在update语句中
@DynamicUpdate
@Entity
@Table(name = "t_user")
public class User {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private Integer age;

}
