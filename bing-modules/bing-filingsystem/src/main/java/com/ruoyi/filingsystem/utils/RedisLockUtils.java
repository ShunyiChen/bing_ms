package com.ruoyi.filingsystem.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RedisLockUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtils.class);
    public RedisTemplate<String, String> redisTemplate;

    /**
     * 构造函数
     * @param redisTemplate
     */
    public RedisLockUtils(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 加锁
     *
     * @param key   redis主键
     * @param value 值
     * @param time  过期时间
     */
    public boolean lock(String key, String value, long time) {
        final boolean result = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.MINUTES));
        if (result) {
            logger.info("[redisTemplate redis]设置锁缓存 缓存  url:{} ========缓存时间为{}分钟", key, time);
        }
        return result;
    }

    /**
     * 解锁
     *
     * @param key redis主键
     * @param value 值
     */
    public boolean unlock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
        if (Objects.equals(1L, result)) {
            logger.info("[redisTemplate redis]释放锁 缓存  url:{}", key);
            return true;
        }
        return false;
    }
}
