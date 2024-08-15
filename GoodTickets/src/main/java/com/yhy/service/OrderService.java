package com.yhy.service;

import com.yhy.entity.dto.OrderRequestDto;

public interface OrderService {
    void addOrder(OrderRequestDto orderRequest) throws Exception;
}
