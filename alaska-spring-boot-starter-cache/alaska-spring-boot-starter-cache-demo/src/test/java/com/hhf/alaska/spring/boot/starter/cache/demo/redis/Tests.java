package com.hhf.alaska.spring.boot.starter.cache.demo.redis;

import com.hhf.alaska.spring.boot.starter.cache.autoconfigure.annotation.CacheableExtend;
import com.hhf.alaska.spring.boot.starter.cache.autoconfigure.redis.RedisCacheableAspect;
import com.hhf.alaska.spring.boot.starter.cache.autoconfigure.utils.SpringUtils;
import com.hhf.alaska.spring.boot.starter.cache.demo.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huang hong fei
 * @date 2019/6/19
 * @description
 **/
public class Tests extends BaseTest {

    @Autowired
    private RedisCacheableAspect redisCacheableAspect;

    @Test
    public void test(){
        System.err.println(redisCacheableAspect);
        this.getUser();
    }

    @CacheableExtend(key = "cacheExpend",expire = 500000)
    public UserDto getUser(){
        UserDto userDto=new UserDto();
        userDto.setUserName("huang hong fei");
        userDto.setPassword("123456");
        return userDto;
    }

    public static class UserDto{
        private String userName;
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
