package com.cc.study.springboot.service.impl;

import com.cc.study.springboot.dao.UserRepository;
import com.cc.study.springboot.model.User;
import com.cc.study.springboot.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author chenc
 * @date 2020/11/11
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository> implements IUserService {
}
