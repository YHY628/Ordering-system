package com.yhy.dao;


import com.yhy.model.Orders;

public interface OrderDao {
    void insertOrder(Orders order) throws Exception;
}
