package com.yhy.mapper;

import com.yhy.entity.po.Restaurant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RestaurantsMapper {
    @Select("select * from restaurants")
    List<Restaurant> selectRestaurants() throws Exception;
    @Select("select * from restaurants where id=#{id}")
    Restaurant selectRestaurantById(String id) throws Exception;
    @Insert("insert into restaurants(id,NAME,address,phone) values(UUID(),#{name},#{address},#{phone})")
    void insertRestaurant(Restaurant restaurant) throws Exception;

    void update(Restaurant restaurant);
}
