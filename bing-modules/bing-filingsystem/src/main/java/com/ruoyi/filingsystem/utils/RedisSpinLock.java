package com.ruoyi.filingsystem.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisSpinLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisSpinLock.class);
    public RedisTemplate<String, String> redisTemplate;

    /**
     * 构造函数
     * @param redisTemplate
     */
    public RedisSpinLock(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 尝试获取锁，直到成功获取或超时
     * @param key 锁的键
     * @param value 锁的值，可以是唯一标识，如线程ID
     * @param expireTime 锁的过期时间（秒）
     * @param timeout 获取锁的超时时间（毫秒）
     * @return 是否成功获取锁
     */
    public boolean tryLock(String key, String value, long expireTime, long timeout) {
        long end = System.currentTimeMillis() + timeout;

        while (System.currentTimeMillis() < end) {
            // 尝试获取锁，使用 setIfAbsent（对应Redis中的SETNX命令）
            Boolean success = redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);

            if (Boolean.TRUE.equals(success)) {
                // 成功获取锁
                return true;
            }

            // 自旋等待一段时间再重试
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /**
     * 释放锁
     * @param key 锁的键
     * @param value 锁的值，确保只有持有锁的线程可以解锁
     */
    public void unlock(String key, String value) {
        // 只有当前持有锁的线程可以解锁
        String currentValue = redisTemplate.opsForValue().get(key);
        if (value.equals(currentValue)) {
            // 删除锁
            redisTemplate.delete(key);
        }
    }
}
