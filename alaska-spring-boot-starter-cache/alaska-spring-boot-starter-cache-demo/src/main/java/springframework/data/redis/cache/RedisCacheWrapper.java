package springframework.data.redis.cache;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.cache.RedisCache;

/**
 * @author 嘿嘿嘿 on 2019/6/19 11:12
 **/
@AllArgsConstructor
public class RedisCacheWrapper {

    private RedisCache redisCache;


    public byte[] createAndConvertCacheKey(Object key) {
        return redisCache.serializeCacheKey(redisCache.createCacheKey(key));
    }

    public byte[] serializeCacheValue(Object value) {
        return redisCache.serializeCacheValue(value);
    }

}
