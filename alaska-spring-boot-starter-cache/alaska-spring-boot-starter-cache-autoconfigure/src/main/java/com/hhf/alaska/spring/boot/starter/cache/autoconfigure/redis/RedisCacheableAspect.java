package com.hhf.alaska.spring.boot.starter.cache.autoconfigure.redis;

import com.hhf.alaska.spring.boot.starter.cache.autoconfigure.annotation.CacheableExtend;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author huang hong fei
 * @date 2019/6/19
 * @description
 **/
@Aspect
public class RedisCacheableAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.hhf.alaska.spring.boot.starter.cache.autoconfigure.annotation.CacheableExtend)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获得当前访问的class
        Class<?> className = joinPoint.getTarget().getClass();
        // 获得访问的方法名
        String methodName = joinPoint.getSignature().getName();
        // 得到方法的参数的类型
        Class<?>[] argClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Object[] args = joinPoint.getArgs();
        String key = "";
        long expireTime = 1000;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            method.setAccessible(true);
            // 判断是否存在@ExtCacheable注解
            if (method.isAnnotationPresent(CacheableExtend.class)) {
                CacheableExtend annotation = method.getAnnotation(CacheableExtend.class);
                key = getRedisKey(args,annotation);
                expireTime = getExpireTime(annotation);
            }
        } catch (IllegalCacheKeyException e) {
            return joinPoint.proceed();
        }
       try {
            //执行原方法（java反射执行method获取结果）
           Object res = joinPoint.proceed();
           //设置过期时间
           redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
           return res;
       }catch (CachingException e){
            e.printStackTrace();
       }
       return joinPoint.proceed();
    }

    private long getExpireTime(CacheableExtend annotation) {
        return annotation.expire();
    }

    private String getRedisKey(Object[] args, CacheableExtend annotation) {
        String primalKey = annotation.key();
        //获取#p0...集合
        List<String> keyList = getKeyParsList(primalKey);
        for (String keyName : keyList) {
            int keyIndex = Integer.parseInt(keyName.toLowerCase().replace("#p", ""));
            Object parValue = args[keyIndex];
            primalKey = primalKey.replace(keyName, String.valueOf(parValue));
        }
        return primalKey.replace("+","").replace("'","");
    }

    /**
     * 获取key中#p0中的参数名称
     * @param key
     * @return
     */
    private static List<String> getKeyParsList(String key) {
        List<String> ListPar = new ArrayList<String>();
        if (key.indexOf("#") >= 0) {
            int plusIndex = key.substring(key.indexOf("#")).indexOf("+");
            int indexNext = 0;
            String parName = "";
            int indexPre = key.indexOf("#");
            if(plusIndex>0){
                indexNext = key.indexOf("#") + key.substring(key.indexOf("#")).indexOf("+");
                parName = key.substring(indexPre, indexNext);
            }else{
                parName = key.substring(indexPre);
            }
            ListPar.add(parName.trim());
            key = key.substring(indexNext + 1);
            if (key.indexOf("#") >= 0) {
                ListPar.addAll(getKeyParsList(key));
            }
        }
        return ListPar;
    }
}
