package com.hhf.alaska.spring.boot.starter.cache.autoconfigure.redis;

/**
 * @author huang hong fei
 * @date 2019/6/19
 * @description
 **/
public class CachingException extends RuntimeException{

    public CachingException(String message){
        super(message);
    }
}
