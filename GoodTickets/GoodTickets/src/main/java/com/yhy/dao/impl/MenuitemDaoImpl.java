package com.yhy.dao.impl;

import com.yhy.dao.MenuitemDao;
import com.yhy.model.MenuItem;
import com.yhy.utils.ThreadLocalConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuitemDaoImpl implements MenuitemDao {
    @Override
    public List<MenuItem> selectMenuitem(String id) throws Exception {
        List<MenuItem> list = new ArrayList<>();
        Connection conn= ThreadLocalConnections.get();
        String sql = "select * from menuitem where restaurant_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            MenuItem item = new MenuItem();
            item.setId(rs.getString("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getBigDecimal("price"));
            item.setImageUrl(rs.getString("imageurl"));
            list.add(item);
        }
        return list;

    }

    @Override
    public MenuItem selectMenuitemById(String id) throws Exception {
        System.out.println("55555");
        MenuItem item = new MenuItem();
        Connection conn=ThreadLocalConnections.get();
        String sql = "select * from menuitem where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            item.setId(id);
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getBigDecimal("price"));
            item.setImageUrl(rs.getString("imageurl"));
        }
        return item;
    }

    @Override
    public void insertMenuitem(MenuItem menuitem) throws Exception {
        Connection conn= ThreadLocalConnections.get();
        String sql = "insert into menuitem(id,restaurant_id,NAME,description,price,imageUrl) values(UUID(),?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, menuitem.getRestaurant());
        pstmt.setString(2, menuitem.getName());
        pstmt.setString(3, menuitem.getDescription());
        pstmt.setBigDecimal(4, menuitem.getPrice());
        pstmt.setString(5, menuitem.getImageUrl());
        pstmt.executeUpdate();
    }
}
