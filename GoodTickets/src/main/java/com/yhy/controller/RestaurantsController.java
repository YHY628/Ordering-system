package com.yhy.controller;

import com.yhy.model.Restaurant;
import com.yhy.resp.R;
import com.yhy.service.impl.RestaurantsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RestaurantsController {
    private RestaurantsServiceImpl restaurantsService = new RestaurantsServiceImpl();

    public void setRestaurantsService(RestaurantsServiceImpl restaurantsService) {
        this.restaurantsService = restaurantsService;
    }
    public R<?> getRestaurants() throws Exception{
        List<Restaurant> restaurantslist=restaurantsService.getRestaurants();
        return R.SUCCESS("获取成功",restaurantslist);
    }
    public R<String>addRestaurant(Restaurant restaurant) throws Exception{
        restaurantsService.addRestaurant(restaurant);
        return R.SUCCESS("添加成功","OK");
    }
    public R<String> manageRestaurant(HttpServletRequest req)throws Exception{
        HttpSession session=req.getSession();
        session.setAttribute("restaurantId", req.getParameter("restaurantId"));

        //转到管理页面
        return R.SUCCESS("进入成功","OK");
    }
}
