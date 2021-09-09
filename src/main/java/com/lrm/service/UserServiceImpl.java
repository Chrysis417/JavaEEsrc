package com.lrm.service;

import com.lrm.dao.UserRepository;
import com.lrm.po.Tag;
import com.lrm.po.User;
import com.lrm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by limi on 2017/10/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser_Login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
    @Override
    public Boolean checkUser_Register(String username){
        User user = userRepository.findByUsername(username);
        return (user == null);
    }
    @Transactional
    @Override
    public User saveUser(User user) { return userRepository.save(user); }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
