package com.yhy.component;


import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.yhy.entity.constants.Constants;
import com.yhy.entity.dto.RestaurantDto;
import com.yhy.entity.po.Restaurant;
import com.yhy.exception.BusinessException;
import com.yhy.utils.StringTools;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


@Component("redisUtils")
public class RedisUtils<V> {
    @Resource
    private RedisTemplate<String,V> redisTemplate;

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    //String缓存放入
    public boolean set(String key, V value) {
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //String缓存放入 设置过期时间
    public boolean setex(String key, V value, long time, TimeUnit unit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, unit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //String缓存写入，并设置逻辑过期时间
    public void setLogex(String key, V value, long time, TimeUnit unit) {


    }
    //String缓冲获取
    public V get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    //hash缓存放入
    public void hset(String key,String key1,V value){
        redisTemplate.opsForHash().put(key,key1,value);
    }
    //hash缓存全部放入
    public void hsetall(String key,Map<String,V> value){
        redisTemplate.opsForHash().putAll(key,value);
    }
    //hash缓存获取
    public Map<Object,Object> hget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    //缓存穿透
    public <V>V queryWithPassThrough(String keyPrefix, V id, Class<R> type, Function<V,V> dbFallback,Long time, TimeUnit unit){
        String key = keyPrefix + id;
        //1.从redis查询缓存
        String json = redisTemplate.opsForValue().get(key);
        //2.判断是否存在
        if(!StringTools.isEmpty(json)){

            //3.存在直接返回
            return JSONUtil.toBean(json,type);
        }
        //4.不存在，根据id查询数据库
        V r = dbFallback.apply(id);
        //5.不存在，返回错误
        if(r == null){
            //将空值写入redis
            setex(key,"", Constants.REDIS_KEY_EXPIRES_Time_2,TimeUnit.MINUTES);
            return null;

        }
        //6.存在，写入redis缓存
        setex(key,r,time,unit);
        //7.返回
        return r;
    }
}
