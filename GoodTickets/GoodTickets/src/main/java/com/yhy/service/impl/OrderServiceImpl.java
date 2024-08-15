package com.yhy.service.impl;


import com.yhy.dao.impl.MenuitemDaoImpl;
import com.yhy.dao.impl.OrderDaoImpl;
import com.yhy.dao.impl.OrderItemsDaoImpl;
import com.yhy.dto.OrderRequestDto;
import com.yhy.exception.LoginException;
import com.yhy.model.MenuItem;
import com.yhy.model.OrderItems;
import com.yhy.model.Orders;
import com.yhy.service.OrderService;
import com.yhy.utils.ThreadLocalConnections;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Setter
public class OrderServiceImpl implements OrderService {
    private OrderDaoImpl orderDao ;
    private OrderItemsDaoImpl orderItemsDao ;
    private MenuitemDaoImpl menuItemDao;

    @Override
    public void addOrder(OrderRequestDto orderRequest) throws Exception {
        ThreadLocalConnections.setAutoCommit(false);
        Orders order = new Orders();
        //为订单创建唯一ID
        String orderId = UUID.randomUUID().toString();
        order.setOrderid(orderId);
        order.setCustomerid(orderRequest.getUserid());
        order.setRestaurantid(orderRequest.getRestaurantid());
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());

        BigDecimal totalAmount = BigDecimal.ZERO;
        //订单详情
        List<OrderItems> list=new ArrayList<>();
        for(OrderRequestDto.MenuItemDto item:orderRequest.getMenuItems()){
            String itemid=item.getId();
            if(itemid==null){
                throw new Exception("未找到该菜单");
            }
            MenuItem menuItem =menuItemDao.selectMenuitemById(itemid);
            OrderItems orderItem = new OrderItems();
            String orderitemId = UUID.randomUUID().toString();
            orderItem.setId(orderitemId);
            orderItem.setOrderId(orderId);
            orderItem.setItemId(itemid);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setTotal((menuItem.getPrice().multiply(new BigDecimal(item.getQuantity()))));
            totalAmount = totalAmount.add(orderItem.getTotal());
            list.add(orderItem);
            orderItemsDao.insertOrderItems(orderItem);
        }
        order.setOrderItemsList(list);
        order.setTotal_amount(totalAmount);
        order.setFinal_amount(totalAmount);//可以处理折扣金额
        order.setOrderstatus("等待商家接单");

        orderDao.insertOrder(order);
        ThreadLocalConnections.commit();
    }
}
