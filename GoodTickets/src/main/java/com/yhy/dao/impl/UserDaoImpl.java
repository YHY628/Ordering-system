package com.yhy.dao.impl;


import com.yhy.dao.UserDao;
import com.yhy.model.User;
import com.yhy.utils.ThreadLocalConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public User selectbyphone(String phone) throws Exception {
        User user = null;
        Connection conn = ThreadLocalConnections.get();
        String sql = "select * from tb_users where phone = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, phone);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user = new User(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3));
        }
        return user;
    }

    @Override
    public void insertUser(User user) throws Exception {
        Connection conn = ThreadLocalConnections.get();
        String sql = "insert into tb_users(id,username,PASSWORD,phone) values(UUID(),?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getPhone());
        ps.executeUpdate();
    }
}
