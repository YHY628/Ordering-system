package com.yhy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yhy.component.RedisComponent;
import com.yhy.entity.dto.RestaurantDto;
import com.yhy.exception.BusinessException;
import com.yhy.mapper.RestaurantsMapper;
import com.yhy.entity.po.Restaurant;
import com.yhy.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RestaurantsServiceImpl implements RestaurantsService {

    @Resource
    private RedisComponent redisComponent;
    @Resource
    private RestaurantsMapper restaurantsMapper;

    //查询商户信息
    public RestaurantDto getRestaurantById(String id) throws Exception{
        //缓冲穿透
        //1.从redis查询商户缓存
        RestaurantDto restaurantDto=redisComponent.getShop(id);
        //2.判断是否存在
        if(restaurantDto!=null){
            //判断是否命中空值
            if(restaurantDto.getId()==null){
                throw new BusinessException("商户不存在");
            }
            //3.存在直接返回
            return restaurantDto;
        }
        //4.不存在，根据id查询数据库
        Restaurant restaurant=restaurantsMapper.selectRestaurantById(id);
        if(restaurant==null){
            //将空值写入redis
            redisComponent.saveNull(id);
            //5.不存在，返回错误
            throw new BusinessException("商户不存在");

        }
        //6.存在，写入redis缓存
        redisComponent.saveShop(id, BeanUtil.copyProperties(restaurant,RestaurantDto.class));
        //7.返回
        return restaurantDto;
    }

    //查询商户信息1
    public RestaurantDto getRestaurantById1(String id) throws Exception{
        //缓冲击穿
        //1.从redis查询商户缓存
        RestaurantDto restaurantDto=redisComponent.getShop(id);
        //2.判断是否存在
        if(restaurantDto!=null){
            //判断是否命中空值
            if(restaurantDto.getId()==null){
                throw new BusinessException("商户不存在");
            }
            //3.存在直接返回
            return restaurantDto;
        }
        //4实现缓冲重建
        //4.1获取互斥锁
        String lockKey="lock:shop:"+id;
//boolean isLock=tryLock(lockKey);
        //4.2判断是否获取成功
        //if(！isLock){
            //4.3失败，则休眠并递归重试


      //  }
        //4.4成功，根据id查询数据库
        Restaurant restaurant=restaurantsMapper.selectRestaurantById(id);
        if(restaurant==null){
            //将空值写入redis
            redisComponent.saveNull(id);
            //5.不存在，返回错误
            throw new BusinessException("商户不存在");

        }
        //6.存在，写入redis缓存
        redisComponent.saveShop(id, BeanUtil.copyProperties(restaurant,RestaurantDto.class));
        //7，释放互斥锁

        //8.返回
        return restaurantDto;
    }




    @Override
    public List<Restaurant> getRestaurants() throws Exception {
        return restaurantsMapper.selectRestaurants();
    }
    //缓冲与数据库双写一致
    @Override
    @Transactional
    public void updateRestaurant(Restaurant restaurant) throws Exception {
        String id=restaurant.getId();
        //1.更新数据库
        restaurantsMapper.update(restaurant);
        //2.删除缓存
        redisComponent.deleteShop(id);
    }
}
