package com.yhy.mapper;

import com.yhy.entity.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectbyphone(String phone) throws Exception;
    void insertUser(User user) throws Exception;
}
