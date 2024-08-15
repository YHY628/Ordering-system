package com.yhy.mapper;

import com.yhy.entity.po.Orders;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    @Insert("insert into orders(id,user_id,restaurant_id,delivery_address,delivery_phone,total_amount,discount_amount,final_amount,status) values" +
            "(#{orderid},#{customerid},#{restaurantid},#{address},#{phone},#{total_amount},#{discount_amount},#{final_amount},#{orderstatus})")
    void insertOrder(Orders order) throws Exception;
}
