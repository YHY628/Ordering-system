package com.yhy.mapper;

import com.yhy.model.OrderItems;
import org.apache.ibatis.annotations.Insert;

import java.math.BigDecimal;

public interface OrderItemsMapper {
    @Insert("insert into order_items(id,order_id,menu_item_id,quantity,price,total) values" +
            "(#{id},#{orderId},#{itemId},#{quantity},#{price},#{total})")
    void insertOrderItems(OrderItems orderItems)throws Exception;
}

