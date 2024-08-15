package com.yhy.entity.po;


import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Setter
@Getter
@NoArgsConstructor
@ToString
@Data
public class MenuItem {
    private String id;
    private String restaurant;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Timestamp createdAt;

}
