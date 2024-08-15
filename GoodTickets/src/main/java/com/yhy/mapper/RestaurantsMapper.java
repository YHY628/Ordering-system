package com.yhy.mapper;

import com.yhy.model.Restaurant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RestaurantsMapper {
    @Select("select * from restaurants")
    List<Restaurant> selectRestaurants() throws Exception;
    @Insert("insert into restaurants(id,NAME,address,phone) values(UUID(),#{name},#{address},#{phone})")
    void insertRestaurant(Restaurant restaurant) throws Exception;
}
