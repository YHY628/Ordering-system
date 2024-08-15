package com.yhy.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Setter
@Getter
@NoArgsConstructor
@ToString
public class MenuItem {
    private String id;
    private String restaurant;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Timestamp createdAt;

}
