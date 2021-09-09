package com.lrm.service;

import com.lrm.po.User;

/**
 * Created by limi on 2017/10/15.
 */
public interface UserService {

    User checkUser_Login(String username, String password);
    Boolean checkUser_Register(String username);
    User saveUser(User user);
    User getUserByUsername(String name);
}
