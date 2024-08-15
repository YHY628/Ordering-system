package com.yhy.service;

import com.yhy.model.User;

public interface UserService {
    User findUserByphone(String phone) throws Exception;
    void addUser(User user) throws Exception;
}
