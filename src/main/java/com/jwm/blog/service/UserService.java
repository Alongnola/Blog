package com.jwm.blog.service;

import com.jwm.blog.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
