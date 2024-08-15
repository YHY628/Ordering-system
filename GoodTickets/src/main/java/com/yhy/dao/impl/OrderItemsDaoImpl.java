package com.yhy.dao.impl;

import com.yhy.dao.OrderItemsDao;
import com.yhy.model.OrderItems;
import com.yhy.utils.ThreadLocalConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class OrderItemsDaoImpl implements OrderItemsDao {

    @Override
    public void insertOrderItems(OrderItems orderItems) throws Exception {
        Connection con = ThreadLocalConnections.get();
        String sql_1 = "insert into order_items(id,order_id,menu_item_id,quantity,price,total) values(?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql_1);
        pst.setObject(1,orderItems.getId());
        pst.setString(2, orderItems.getOrderId());
        pst.setObject(3, orderItems.getItemId());
        pst.setObject(4, orderItems.getQuantity());
        pst.setObject(5, orderItems.getPrice());
        pst.setObject(6, orderItems.getTotal());
        pst.executeUpdate();
    }
}


