package com.chingshan.springbootmail.service.impl;

import com.chingshan.springbootmail.service.redisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;


@Component
public class redisTestServiceImpl implements redisTestService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveUser(String userId, String user) {
        // 將用戶資料存入 Redis，並設置過期時間
        redisTemplate.opsForValue().set(userId, user, 10, TimeUnit.MINUTES);
    }

    @Override
    public Object getUser(String userId) {
        // 從 Redis 中取得用戶資料
        return redisTemplate.opsForValue().get(userId);
    }

    @Override
    public void deleteUser(String userId) {
        // 刪除 Redis 中的用戶資料
        redisTemplate.delete(userId);
    }
}
