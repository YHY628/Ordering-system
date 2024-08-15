package com.yhy.dao;

import com.yhy.model.Restaurant;

import java.util.List;

public interface RestaurantsDao {
    List<Restaurant> selectRestaurants() throws Exception;
    void insertRestaurant(Restaurant restaurant) throws Exception;

}
