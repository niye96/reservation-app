package com.ices.reservation.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ny
 * @Date: Created in 14:13 2018/5/8 0008
 */
@Service
public class RedisService {
//    @Autowired
//    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

//    public Object get(String key){
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public void set(String key, Object val){
//        redisTemplate.opsForValue().set(key, val);
//    }
//
//    public boolean expire(String key, long timeout, TimeUnit timeUnit){
//        return redisTemplate.expire(key, timeout, timeUnit);
//    }

    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setString(String key, String val){
        stringRedisTemplate.opsForValue().set(key, val);
    }

    public boolean expireString(String key, long timeout, TimeUnit timeUnit){
        return stringRedisTemplate.expire(key, timeout, timeUnit);
    }
}
