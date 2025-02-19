package com.bing.common.redis.configure;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SslOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.File;
import java.io.IOException;

/**
 * redis配置
 * 
 * @author Simeon
 */
@Configuration
@EnableCaching
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${bing.redis.host}")
    private String host;
    @Value("${bing.redis.port}")
    private Integer port;
    @Value("${bing.redis.username: }")
    private String username;
    @Value("${bing.redis.password: }")
    private String password;
    @Value("${bing.redis.database}")
    private Integer database;
    @Value("${bing.redis.useSsl:false}")
    private Boolean useSsl;
    @Value("${bing.redis.useJks:false}")
    private Boolean useJks;
    @Value("${bing.redis.jks}")
    private String jsk;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() throws IOException {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setHostName(host);
        if (StringUtils.isNotBlank(username)) {
            redisStandaloneConfiguration.setUsername(username);
        }
        if (StringUtils.isNotBlank(password)) {
            redisStandaloneConfiguration.setPassword(password);
        }
        LettuceClientConfiguration lettuceClientConfiguration = null;
        if (useSsl && useJks) {
            ClassPathResource classPathResource = new ClassPathResource(jsk);
            String filePath = this.getClass().getClassLoader().getResource(jsk).getFile();
            File file = new File(filePath);
            FileUtils.copyToFile(classPathResource.getInputStream(), file);
            SslOptions sslOptions = SslOptions.builder()
                    .jdkSslProvider()
                    .truststore(file)
                    .build();
            ClientOptions clientOptions = ClientOptions.builder()
                    .sslOptions(sslOptions).build();
            lettuceClientConfiguration = LettuceClientConfiguration.builder().clientOptions(clientOptions).useSsl().build();
        }
        else if(!useJks && useSsl) {
            lettuceClientConfiguration = LettuceClientConfiguration.builder().useSsl().build();
        }
        else {
            lettuceClientConfiguration = LettuceClientConfiguration.builder().build();
        }
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object>  redisTemplate() throws IOException {
        // 原RedisTemplate定义
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
