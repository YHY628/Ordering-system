package com.yhy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String opening_hours;
    private Timestamp createdAt;

}
