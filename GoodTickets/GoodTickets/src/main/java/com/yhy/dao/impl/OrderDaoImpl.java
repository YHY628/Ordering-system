package com.yhy.dao.impl;

import com.yhy.dao.OrderDao;
import com.yhy.model.Orders;
import com.yhy.utils.ThreadLocalConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class OrderDaoImpl implements OrderDao {
    @Override
    public void insertOrder(Orders order) throws Exception {
        Connection con = ThreadLocalConnections.get();
        String sql = "insert into orders(id,user_id,restaurant_id,delivery_address,delivery_phone,total_amount,discount_amount,final_amount,status) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1,order.getOrderid());
        ps.setObject(2,order.getCustomerid());
        ps.setObject(3,order.getRestaurantid());
        ps.setObject(4,order.getAddress());
        ps.setObject(5,order.getPhone());
        ps.setObject(6,order.getTotal_amount());
        ps.setObject(7,order.getDiscount_amount());
        ps.setObject(8,order.getFinal_amount());
        ps.setObject(9,order.getOrderstatus());
        ps.executeUpdate();
    }
}
