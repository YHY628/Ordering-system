package com.yhy.servlet;

import com.yhy.controller.MenuitemController;
import com.yhy.controller.OrderControlller;
import com.yhy.controller.RestaurantsController;
import com.yhy.controller.UserController;
import com.yhy.dao.impl.*;
import com.yhy.dto.OrderRequestDto;
import com.yhy.exception.InfoException;
import com.yhy.exception.LoginException;
import com.yhy.model.MenuItem;
import com.yhy.model.Restaurant;
import com.yhy.model.User;
import com.yhy.resp.R;
import com.yhy.service.impl.MenuitemServiceImpl;
import com.yhy.service.impl.OrderServiceImpl;
import com.yhy.service.impl.RestaurantsServiceImpl;
import com.yhy.service.impl.UserServiceImpl;
import com.yhy.utils.DatabasePoolUtil;
import com.yhy.utils.JsonWriter;
import com.yhy.utils.PropsUtil;
import com.yhy.utils.ThreadLocalConnections;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
    private final static String CONFIG_LOCATION = "databaseConfigLocation";

    private Map<String, HttpHandler> handlerMap;

    private UserController userController;
    private RestaurantsController restaurantsController;
    private MenuitemController menuitemController;
    private OrderControlller orderController;



    @Override
    public void init(ServletConfig config) {
        // 数据库初始化
        String configLocation = config.getInitParameter(CONFIG_LOCATION); // 获取文件路径
        Properties properties = PropsUtil.readConfig(configLocation); // 读取文件内容
        DatabasePoolUtil.init(properties); // 初始化数据库

        // 初始化 controller  service  dao 组件
        userController = new UserController();
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        userController.setUserService(userService);
        userService.setUserDao(userDao);

        restaurantsController=new RestaurantsController();
        RestaurantsDaoDaoImpl restaurantsDao = new RestaurantsDaoDaoImpl();
        RestaurantsServiceImpl restaurantsService = new RestaurantsServiceImpl();
        restaurantsController.setRestaurantsService(restaurantsService);
        restaurantsService.setRestaurantsDao(restaurantsDao);

        menuitemController=new MenuitemController();
        MenuitemDaoImpl menuitemDao = new MenuitemDaoImpl();
        MenuitemServiceImpl menuitemService = new MenuitemServiceImpl();
        menuitemController.setMenuitemService(menuitemService);
        menuitemService.setDao(menuitemDao);

        orderController=new OrderControlller();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        MenuitemDaoImpl menuitemDao2 = new MenuitemDaoImpl();
        OrderItemsDaoImpl orderItemsDao = new OrderItemsDaoImpl();
        orderController.setOrderService(orderService);
        orderService.setOrderDao(orderDao);
        orderService.setMenuItemDao(menuitemDao2);
        orderService.setOrderItemsDao(orderItemsDao);



        // 初始化 handlerMap
        handlerMap = new HashMap<>();
        handlerMap.put("d/user/login", this::handerLogin);
        handlerMap.put("d/user/reguser", this::handerReg);
        handlerMap.put("d/restaurant/reg", this::handeraddrestaurant);
        handlerMap.put("d/restaurant/get", this::handergetrestaurant);
        handlerMap.put("d/restaurant/manageRestaurant",this::manageRestaurant);
        handlerMap.put("d/menuitem/add",this::handeraddmenit);
        handlerMap.put("d/menuitem/get", this::handergetmenuitem);
        handlerMap.put("d/order/summbit",this::handersummenitoder);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("###################");
        R<?> r; // 声明统一的响应格式
        String uri = req.getRequestURI();
        String path = uri.substring(1); // 去掉前导斜杠
        HttpHandler handler = handlerMap.get(path);
        try {
            ThreadLocalConnections.set();
            r=handler.handle(req, resp); // 调用对应的处理函数
            JsonWriter.writer(r, resp); // 响应客户端

        }catch (LoginException e){
            JsonWriter.writer(R.LOGIN_FAIL(e.getMessage()),resp);
        } catch (InfoException e) {
            JsonWriter.writer(R.INFO_ERROR(e.getMessage()), resp);
        } catch (Exception e) {
            if (!ThreadLocalConnections.isAutoCommit()) {
                ThreadLocalConnections.rollback();
            }
            JsonWriter.writer(R.FAIL(e.getMessage()), resp); // 响应异常
            e.printStackTrace();
        } finally {
            // 关闭数据库
            ThreadLocalConnections.close();
        }

    }


    private R<?> handerLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String phone = req.getParameter("phone");
        System.out.println(phone);
        String password = req.getParameter("password");
        User user= new User();
        user.setPhone(phone);
        user.setPassword(password);
        return userController.login(user,req);
    }

    private R<String> handerReg(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setUsername(username);
        return userController.reguser(user);
    }

    private R<String>handeraddrestaurant(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        Restaurant restaurant=new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);
        return restaurantsController.addRestaurant(restaurant);
    }

    private R<?> handergetrestaurant(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return restaurantsController.getRestaurants();
    }
    private R<String> manageRestaurant(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return  restaurantsController.manageRestaurant(req);
    }

    private R<String>handeraddmenit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.valueOf(req.getParameter("price"));
        String image = req.getParameter("image");
        MenuItem menuItem=new MenuItem();
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(BigDecimal.valueOf(price));
        menuItem.setImageUrl(image);
        return menuitemController.addMenuitem(menuItem,req);
    }

    private R<?> handergetmenuitem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return menuitemController.getMenuitem(req);
    }

    private R<?> handersummenitoder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session=req.getSession(false);
        String userID = (String) session.getAttribute("userId");
        String restaurantID = (String) session.getAttribute("restaurantId");
        OrderRequestDto orderRequestDto=new OrderRequestDto();
        orderRequestDto.setUserid(userID);
        orderRequestDto.setRestaurantid(restaurantID);
        orderRequestDto.setAddress(req.getParameter("adderss"));
        orderRequestDto.setPhone(req.getParameter("phone"));

        String[] menuItemIds = req.getParameterValues("menuItemId");
        String[] quantities = req.getParameterValues("quantity");
        if (menuItemIds != null && quantities != null && menuItemIds.length == quantities.length) {
            List<OrderRequestDto.MenuItemDto> list = new ArrayList<>();
            for (int i = 0; i < menuItemIds.length; i++) {
                OrderRequestDto.MenuItemDto menuItemDto = new OrderRequestDto.MenuItemDto();
                menuItemDto.setId(menuItemIds[i]);
                menuItemDto.setQuantity(Integer.valueOf(quantities[i]));
                list.add(menuItemDto);
            }
            orderRequestDto.setMenuItems(list);
        }
        return orderController.addOrder(orderRequestDto);
    }
    @FunctionalInterface
    interface HttpHandler {
        R<?> handle(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    }
}
