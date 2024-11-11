package com.chingshan.springbootmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Component
public class TokenBucketService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String TOKEN_BUCKET_KEY = "token_bucket";
    private static final int MAX_TOKENS = 0;    // 桶的最大容量
    private static final int REFILL_RATE = 0;    // 每秒新增的 Token 數量

    // 初始化 Token Bucket
    public void initializeBucket() {
        if (!redisTemplate.hasKey(TOKEN_BUCKET_KEY)) {
            redisTemplate.opsForValue().set(TOKEN_BUCKET_KEY, String.valueOf(MAX_TOKENS), 1, TimeUnit.HOURS);
        }
    }

    // 消耗 Token 方法
    public boolean consumeToken() {
        // 獲取當前 Token 數量
        String tokenCountStr = redisTemplate.opsForValue().get(TOKEN_BUCKET_KEY);
        int tokenCount = (tokenCountStr != null) ? Integer.parseInt(tokenCountStr) : 0;

        if (tokenCount > 0) {
            // 如果有足夠的 Token，消耗一個並更新數量
            redisTemplate.opsForValue().decrement(TOKEN_BUCKET_KEY);
            return true;
        } else {
            // 沒有 Token，拒絕請求
            return false;
        }
    }

    // 定期新增 Token
    public void refillTokens() {
        redisTemplate.opsForValue().increment(TOKEN_BUCKET_KEY, REFILL_RATE);
        int currentTokens = Integer.parseInt(redisTemplate.opsForValue().get(TOKEN_BUCKET_KEY));
        if (currentTokens > MAX_TOKENS) {
            // 保證不超過最大容量
            redisTemplate.opsForValue().set(TOKEN_BUCKET_KEY, String.valueOf(MAX_TOKENS));
        }
    }
}
