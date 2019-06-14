package com.hhf.alaska.spring.boot.starter.zuul.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author huanghongfei
 * @Description description
 * @Date Create in 10:08 2018/5/4
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    /**
     * 将对象转为json
     * @param o
     * @return
     */
    public static String toJson(Object o){
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json,Class<T> clazz){
        try {
            return mapper.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转数组对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz){
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        List<T> ts= null;
        try {
            ts = mapper.readValue(json,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ts;
    }

    /*public static void main(String[] args) {
        System.err.println(JsonUtils.toJson(new String("1")));
    }*/
}
