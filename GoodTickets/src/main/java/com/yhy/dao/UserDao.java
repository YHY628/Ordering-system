package com.yhy.dao;

import com.yhy.model.User;

public interface UserDao {
    public User selectbyphone(String phone) throws Exception;
    public void insertUser(User user) throws Exception;
}
