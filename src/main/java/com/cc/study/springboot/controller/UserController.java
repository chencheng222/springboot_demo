package com.cc.study.springboot.controller;

import com.cc.study.springboot.model.User;
import com.cc.study.springboot.service.IUserService;
import com.cc.study.springboot.service.impl.BaseServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenc
 * @date 2020/11/11
 **/
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<User, String, IUserService> {
}
