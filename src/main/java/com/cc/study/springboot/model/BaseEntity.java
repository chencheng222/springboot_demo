package com.cc.study.springboot.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author chenc
 * @date 2020/11/11
 **/
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name="uuidhex",strategy="uuid.hex")
    @GeneratedValue(generator="uuidhex")
    @Column(name="id",length=32,nullable=false)
    protected String id;
}
