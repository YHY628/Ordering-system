package com.yhy.mapper;

import com.yhy.model.User;

public interface UserMapper {
    User selectbyphone(String phone) throws Exception;
    void insertUser(User user) throws Exception;
}
