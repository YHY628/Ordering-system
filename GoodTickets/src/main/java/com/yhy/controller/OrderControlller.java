package com.yhy.controller;

import com.yhy.entity.dto.OrderRequestDto;
import com.yhy.resp.R;
import com.yhy.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("d/order/")
public class OrderControlller {
    @Autowired
    private OrderServiceImpl orderService ;
    @RequestMapping("summit")
    public R<?> addOrder(OrderRequestDto orderRequestDto) throws Exception {
        orderService.addOrder(orderRequestDto);
        return R.SUCCESS("下单成功","OK");
    }
}
