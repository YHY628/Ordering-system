package com.yhy.service;

import com.yhy.entity.po.Restaurant;

import java.util.List;

public interface RestaurantsService {
    List<Restaurant> getRestaurants() throws Exception;
    void updateRestaurant(Restaurant restaurant) throws Exception;
}
