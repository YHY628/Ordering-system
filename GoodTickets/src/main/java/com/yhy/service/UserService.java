package com.yhy.service;

import com.yhy.entity.dto.UserDto;
import com.yhy.entity.po.User;

public interface UserService {
    User findUserByphone(String phone) throws Exception;
    void sendCode(String phone);
    UserDto getUser(String token) throws Exception;
    String login(String phone,String code) throws Exception;
}
