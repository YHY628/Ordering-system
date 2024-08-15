package com.yhy.service.impl;


import com.yhy.entity.dto.OrderRequestDto;
import com.yhy.mapper.MenuitemMapper;
import com.yhy.mapper.OrderItemsMapper;
import com.yhy.mapper.OrderMapper;
import com.yhy.entity.po.MenuItem;
import com.yhy.entity.po.OrderItems;
import com.yhy.entity.po.Orders;
import com.yhy.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private MenuitemMapper menuitemMapper ;
    @Resource
    private OrderMapper orderMapper ;
    @Resource
    private OrderItemsMapper orderItemsMapper ;

    @Override
    @Transactional
    public void addOrder(OrderRequestDto orderRequest) throws Exception {

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
            MenuItem menuItem =menuitemMapper.selectMenuitemById(itemid);
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
            orderItemsMapper.insertOrderItems(orderItem);
        }
        order.setOrderItemsList(list);
        order.setTotal_amount(totalAmount);
        order.setFinal_amount(totalAmount);//可以处理折扣金额
        order.setOrderstatus("等待商家接单");

        orderMapper.insertOrder(order);
    }
}
