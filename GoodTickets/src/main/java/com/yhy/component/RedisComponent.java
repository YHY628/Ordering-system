package com.yhy.component;

import cn.hutool.json.JSONUtil;
import com.yhy.entity.constants.Constants;
import com.yhy.entity.dto.RestaurantDto;
import com.yhy.entity.dto.UserDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("redisComponent")
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;

    //保存空值
    public void saveNull(String id){
        redisUtils.setex(id,"",Constants.REDIS_KEY_EXPIRES_Time_2);
    }

    //保存验证码
    public void saveCode(String phone, String code) {
        redisUtils.setex(Constants.REDIS_KEY_CODE+phone, code, Constants.REDIS_KEY_EXPIRES_Time_15);
    }
    //获取验证码
    public String getCode(String phone) {
        return (String) redisUtils.get(Constants.REDIS_KEY_CODE+phone);
    }

    //保存用户信息
    public void saveUserinfo(String token, Map<String,Object>usermap) {
        redisUtils.hsetall(Constants.REDIS_KEY_TOKEN+token,usermap);
    }
    //获取用户信息
    public UserDto getUserinfo(String token) {
        Map<String,Object> usermap = redisUtils.hget(Constants.REDIS_KEY_TOKEN+token);
        UserDto userDto = new UserDto();
        if(usermap != null) {
            userDto.setPhone(usermap.get("phone").toString());
            userDto.setUsername(usermap.get("username").toString());
        }
        return userDto;
    }
    //保存商户信息
    public void saveShop(String id , RestaurantDto restaurantDto){
        String shopJson= JSONUtil.toJsonStr(restaurantDto);
        redisUtils.setex(Constants.REDIS_KEY_SHOP+id, shopJson, Constants.REDIS_KEY_EXPIRES_Time_15);
    }
    //获取商户信息
    public RestaurantDto getShop(String id){
        return JSONUtil.toBean((String) redisUtils.get(Constants.REDIS_KEY_SHOP+id),RestaurantDto.class);
    }
    //删除缓冲
    public void deleteShop(String id){
        redisUtils.delete(Constants.REDIS_KEY_SHOP+id);
    }




}
