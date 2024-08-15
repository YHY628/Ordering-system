package com.yhy.service.impl;

import com.yhy.dao.impl.RestaurantsDaoDaoImpl;
import com.yhy.model.Restaurant;
import com.yhy.service.RestaurantsService;

import java.util.List;

public class RestaurantsServiceImpl implements RestaurantsService {

    private RestaurantsDaoDaoImpl restaurantsDao = new RestaurantsDaoDaoImpl();

    public void setRestaurantsDao(RestaurantsDaoDaoImpl restaurantsDao) {
        this.restaurantsDao = restaurantsDao;
    }

    @Override
    public List<Restaurant> getRestaurants() throws Exception {
        return restaurantsDao.selectRestaurants();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) throws Exception {
            restaurantsDao.insertRestaurant(restaurant);
    }
}
