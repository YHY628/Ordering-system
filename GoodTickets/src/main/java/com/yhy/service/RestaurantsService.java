package com.yhy.service;

import com.yhy.model.Restaurant;

import java.util.List;

public interface RestaurantsService {
    List<Restaurant> getRestaurants() throws Exception;
    void addRestaurant(Restaurant restaurant) throws Exception;
}
