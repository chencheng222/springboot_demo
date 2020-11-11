package com.cc.study.springboot.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author chenc
 * @date 2020/06/17
 **/
@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    @Column
    private String name;

    @Column
    private Integer age;

}
