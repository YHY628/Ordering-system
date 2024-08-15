package com.yhy.controller;

import com.yhy.model.MenuItem;
import com.yhy.resp.R;
import com.yhy.service.impl.MenuitemServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MenuitemController {
    private MenuitemServiceImpl menuitemService;
    private RestaurantsController restaurantsController;

    public void setRestaurantsController(RestaurantsController restaurantsController) {
        this.restaurantsController = restaurantsController;
    }

    public void setMenuitemService(MenuitemServiceImpl menuitemService) {
        this.menuitemService = menuitemService;
    }

    public R<?> getMenuitem(HttpServletRequest rep) throws Exception {
        HttpSession session=rep.getSession(false);
        String restaurantid = (String) session.getAttribute("restaurantId");
        List<MenuItem> menuItems=menuitemService.getMenuitems(restaurantid);
        return R.SUCCESS("获取成功", menuItems);
    }

    public R<String> addMenuitem(MenuItem menuitem,HttpServletRequest rep) throws Exception {
        HttpSession session=rep.getSession(false);
        menuitem.setRestaurant((String) session.getAttribute("restaurantId"));
        menuitemService.addMenuitems(menuitem);
        return R.SUCCESS("添加成功","OK");
    }
}
