package com.cc.study.springboot.dao;

import com.cc.study.springboot.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author chenc
 * @date 2020/11/11
 **/
@Repository
public interface UserRepository extends BaseRepository<User, String> {

}
