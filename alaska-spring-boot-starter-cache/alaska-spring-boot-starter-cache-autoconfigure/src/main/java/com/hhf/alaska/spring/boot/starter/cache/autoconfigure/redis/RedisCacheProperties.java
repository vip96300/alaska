package com.hhf.alaska.spring.boot.starter.cache.autoconfigure.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huang hong fei
 * @date 2019/6/19
 * @description
 **/
@ConfigurationProperties(prefix="alaska.spring.boot.starter.cache.redis")
public class RedisCacheProperties {

    private long expire;

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
