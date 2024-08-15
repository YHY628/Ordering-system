package com.yhy.dao.impl;

import com.yhy.dao.RestaurantsDao;
import com.yhy.model.Restaurant;
import com.yhy.utils.ThreadLocalConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RestaurantsDaoDaoImpl implements RestaurantsDao {
    @Override
    public List<Restaurant> selectRestaurants() throws Exception {
        List<Restaurant> restaurants=new ArrayList<Restaurant>();
        Connection conn= ThreadLocalConnections.get();
        String sql="select * from restaurants";
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Restaurant restaurant=new Restaurant();
            restaurant.setId(rs.getString("id"));
            restaurant.setName(rs.getString("name"));
            restaurant.setAddress(rs.getString("address"));
            restaurant.setPhone(rs.getString("phone"));
            restaurants.add(restaurant);
        }
        return restaurants;
    }

    @Override
    public void insertRestaurant(Restaurant restaurant) throws Exception {
        Connection conn= ThreadLocalConnections.get();
        String sql="insert into restaurants(NAME,address,phone) values(?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1, restaurant.getName());
        ps.setString(2, restaurant.getAddress());
        ps.setString(3, restaurant.getPhone());
        ps.executeUpdate();
    }
}
