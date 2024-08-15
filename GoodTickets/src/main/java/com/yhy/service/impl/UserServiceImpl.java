package com.yhy.service.impl;

import com.yhy.mapper.UserMapper;
import com.yhy.model.User;
import com.yhy.service.UserService;
import com.yhy.utils.LockUtil;
import com.yhy.utils.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;


public class UserServiceImpl implements UserService {

    private SqlSession sqlSession= MyBatisSqlSessionFactory.getSqlSession();

    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    private final String lockPrefix = "com.yhy.service.UserServiceImpl";

    @Override
    public User findUserByphone(String phone) throws Exception {
        return userMapper.selectbyphone(phone);
    }

    @Override
    public void addUser(User user) throws Exception {

        // 解决账号重复的问题, 有可能2个线程都查询同样的手机号, 2个线程都去注册, 保存原子性
        String lock = (lockPrefix + ".addUser." + user.getPhone()).intern(); // 区别唯一性

        if (!LockUtil.lock(lock)) { // 原子性
            throw new Exception("手机号已经注册");
        }
        User data=userMapper.selectbyphone(user.getPhone());
        if (data!=null) {
            throw new Exception("手机号已经注册");
        }
        // 注册账号
        userMapper.insertUser(user);
        LockUtil.unlock(lock);
    }
}
