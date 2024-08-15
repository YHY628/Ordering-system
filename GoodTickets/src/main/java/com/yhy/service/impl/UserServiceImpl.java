package com.yhy.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.yhy.component.RedisComponent;
import com.yhy.entity.constants.Constants;
import com.yhy.entity.dto.UserDto;
import com.yhy.exception.BusinessException;
import com.yhy.mapper.UserMapper;
import com.yhy.entity.po.User;
import com.yhy.service.UserService;
import com.yhy.utils.LockUtil;
import com.yhy.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RedisComponent redisComponent;
    @Autowired
    private UserMapper userMapper ;


    @Override
    public User findUserByphone(String phone) throws Exception {
        return userMapper.selectbyphone(phone);
    }

    @Override
    public void sendCode(String phone) {
        //2.生成验证码
        String code= StringTools.getRandomNumber(Constants.LENGTH_6);
        //3.保存验证码到redis
        redisComponent.saveCode(phone,code);
        //4.发送验证码


    }
    @Override
    public String login(String phone, String code) throws Exception {
        //校验验证码
        checkCode(phone,code);
        //查询用户
        User user=userMapper.selectbyphone(phone);
        //判断用户是否存在
        if (user==null) {
            user=createUser(phone);
        }
        //保存用户信息到redis
        //随机生成token，作为登录令牌
        String token= UUID.randomUUID().toString(true);
        UserDto userDto= BeanUtil.copyProperties(user,UserDto.class);
        Map<String,Object> usermap=BeanUtil.beanToMap(userDto);
        redisComponent.saveUserinfo(token,usermap);

        return token;

    }
    //获取用户信息
    @Override
    public UserDto getUser(String token) throws Exception {
        return redisComponent.getUserinfo(token);
    }
    //创建用户
    private User createUser(String phone) {
        User user=new User();
        user.setPhone(phone);
        user.setUsername(StringTools.getRandomString(Constants.LENGTH_15));
        return user;
    }
    //校验验证码
    private void checkCode(String phone, String code) {
        //从redis中获取验证码
        String cachecode = redisComponent.getCode(phone);
        if (cachecode == null || !code.equals(cachecode)) {
            throw new BusinessException("验证码不正确");
        }
    }


}
