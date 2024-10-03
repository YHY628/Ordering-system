package com.yhy.controller;

import com.yhy.entity.po.Restaurant;
import com.yhy.resp.R;
import com.yhy.service.impl.RestaurantsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@RestController
@RequestMapping("d/restaurant/")
public class RestaurantsController {
    @Resource
    private RestaurantsServiceImpl restaurantsService = new RestaurantsServiceImpl();

    @RequestMapping("get")
    public R<?> getRestaurants() throws Exception{
        List<Restaurant> restaurantslist=restaurantsService.getRestaurants();
        return R.SUCCESS("获取成功",restaurantslist);
    }
    @RequestMapping("reg")
    public R<String>addRestaurant(Restaurant restaurant) throws Exception{
        //restaurantsService.addRestaurant(restaurant);
        return R.SUCCESS("添加成功","OK");
    }
    @RequestMapping("manageRestaurant")
    public R<String> manageRestaurant(HttpServletRequest req)throws Exception{
        HttpSession session=req.getSession();
        session.setAttribute("restaurantId", req.getParameter("restaurantId"));

        //转到管理页面
        return R.SUCCESS("进入成功","OK");
    }
}
