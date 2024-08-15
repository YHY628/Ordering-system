package com.yhy.mapper;

import com.yhy.entity.po.OrderItems;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsMapper {
    @Insert("insert into order_items(id,order_id,menu_item_id,quantity,price,total) values" +
            "(#{id},#{orderId},#{itemId},#{quantity},#{price},#{total})")
    void insertOrderItems(OrderItems orderItems)throws Exception;
}

