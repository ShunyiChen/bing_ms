package com.bing.common.redis.configure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * 开启Lettuce连接校验功能，来避免出现 io.lettuce.core.RedisCommandTimeoutException: Command timed out
 *
 * 说明：lettuce提供了校验连接的方法，lettuce提供了校验连接的方法 只是默认没开启
 * 开启的话是每次获取连接都会校验，开启获取连接的校验。
 */
@Component
public class LettuceConnectionValidConfig implements InitializingBean {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(redisConnectionFactory instanceof LettuceConnectionFactory){
            System.out.println("====> 已开启Lettuce连接校验功能");
            LettuceConnectionFactory c=(LettuceConnectionFactory)redisConnectionFactory;
            c.setValidateConnection(true);
        }
    }
}
