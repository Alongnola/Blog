package com.jwm.blog.service.Impl;

import com.jwm.blog.mapper.UserMapper;
import com.jwm.blog.pojo.User;
import com.jwm.blog.service.UserService;
import com.jwm.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper dao;

    @Override
    public User checkUser(String username, String password) {
        // 密码加密查询
        User user = dao.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
