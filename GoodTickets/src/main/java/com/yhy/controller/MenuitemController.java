package com.yhy.controller;

import com.yhy.entity.po.MenuItem;
import com.yhy.resp.R;
import com.yhy.service.impl.MenuitemServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@RestController
@RequestMapping("d/menuitem/")
public class MenuitemController {
    private MenuitemServiceImpl menuitemService;
    private RestaurantsController restaurantsController;

    public void setRestaurantsController(RestaurantsController restaurantsController) {
        this.restaurantsController = restaurantsController;
    }

    public void setMenuitemService(MenuitemServiceImpl menuitemService) {
        this.menuitemService = menuitemService;
    }
    @RequestMapping("get")
    public R<?> getMenuitem(HttpServletRequest rep) throws Exception {
        HttpSession session=rep.getSession(false);
        String restaurantid = (String) session.getAttribute("restaurantId");
        List<MenuItem> menuItems=menuitemService.getMenuitems(restaurantid);
        return R.SUCCESS("获取成功", menuItems);
    }
    @RequestMapping("add")
    public R<String> addMenuitem(MenuItem menuitem,HttpServletRequest rep) throws Exception {
        HttpSession session=rep.getSession(false);
        menuitem.setRestaurant((String) session.getAttribute("restaurantId"));
        menuitemService.addMenuitems(menuitem);
        return R.SUCCESS("添加成功","OK");
    }
}
