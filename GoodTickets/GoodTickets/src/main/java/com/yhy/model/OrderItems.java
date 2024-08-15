package com.yhy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItems {
    private String id;
    private String orderId;
    private String itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;

}
