package com.yhy.entity.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    private String orderid;
    private String customerid;
    private String restaurantid;
    private String address;
    private String phone;
    private BigDecimal total_amount;
    private BigDecimal discount_amount;
    private BigDecimal final_amount;
    private String orderstatus;
    private Date orderdate;
    private List<OrderItems> orderItemsList;


}
