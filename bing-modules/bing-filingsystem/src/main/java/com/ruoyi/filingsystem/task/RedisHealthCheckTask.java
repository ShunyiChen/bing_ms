package com.ruoyi.filingsystem.task;

import com.ruoyi.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedisHealthCheckTask {

    @Autowired
    private RedisService redisService;

    @Scheduled(fixedDelay = 5000) // 每隔5秒执行一次
    public void ping() {
        redisService.pingRedis();
    }
}
