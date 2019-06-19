package com.hhf.alaska.spring.boot.starter.cache.autoconfigure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huang hong fei
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheableExtend {

    /**
     * cache key
     * @return
     */
    String key() default "";

    /**
     * cache expire time (default milliseconds)
     * @return
     */
    long expire() default 0;
}
