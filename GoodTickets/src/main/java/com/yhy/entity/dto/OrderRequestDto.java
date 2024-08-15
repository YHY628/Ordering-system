package com.yhy.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class OrderRequestDto {
    private String userid;
    private String restaurantid;
    private String address;
    private String phone;
    private List<MenuItemDto> menuItems;

    // Getters and Setters
@Setter
@Getter
    public static class MenuItemDto {
        private String id;
        private Integer quantity;

        // Getters and Setters
    }
}

