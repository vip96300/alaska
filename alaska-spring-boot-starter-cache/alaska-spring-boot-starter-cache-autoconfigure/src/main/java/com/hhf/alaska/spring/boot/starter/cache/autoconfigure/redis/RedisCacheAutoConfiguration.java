package com.hhf.alaska.spring.boot.starter.cache.autoconfigure.redis;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @author huang hong fei
 * @date 2019/6/19
 * @description
 **/
@Configuration
@AutoConfigureAfter(RedisTemplate.class)
@EnableConfigurationProperties(RedisCacheProperties.class)
public class RedisCacheAutoConfiguration {

    @Bean
    public RedisCacheSupport redisCacheSupport(){
        return new RedisCacheSupport();
    }

    @Bean
    public RedisCacheableAspect redisCacheableAspect(){
        return new RedisCacheableAspect();
    }

}
