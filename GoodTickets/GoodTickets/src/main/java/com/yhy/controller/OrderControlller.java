package com.yhy.controller;

import com.yhy.dto.OrderRequestDto;
import com.yhy.resp.R;
import com.yhy.service.impl.OrderServiceImpl;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderControlller {
    @Setter
    private OrderServiceImpl orderService ;

    public R<?> addOrder(OrderRequestDto orderRequestDto) throws Exception {
        orderService.addOrder(orderRequestDto);
        return R.SUCCESS("下单成功","OK");
    }
}
