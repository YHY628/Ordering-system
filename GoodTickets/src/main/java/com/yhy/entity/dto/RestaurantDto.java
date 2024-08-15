package com.yhy.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class RestaurantDto {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String opening_hours;
    private Timestamp createdAt;
}
