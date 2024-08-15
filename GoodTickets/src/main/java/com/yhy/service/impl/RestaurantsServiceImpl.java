package com.yhy.service.impl;

import com.yhy.mapper.RestaurantsMapper;
import com.yhy.model.Restaurant;
import com.yhy.service.RestaurantsService;
import com.yhy.utils.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RestaurantsServiceImpl implements RestaurantsService {

    private SqlSession sqlSession= MyBatisSqlSessionFactory.getSqlSession();

    private RestaurantsMapper restaurantsMapper= sqlSession.getMapper(RestaurantsMapper.class);
    @Override
    public List<Restaurant> getRestaurants() throws Exception {
        return restaurantsMapper.selectRestaurants();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) throws Exception {
        restaurantsMapper.insertRestaurant(restaurant);
    }
}
