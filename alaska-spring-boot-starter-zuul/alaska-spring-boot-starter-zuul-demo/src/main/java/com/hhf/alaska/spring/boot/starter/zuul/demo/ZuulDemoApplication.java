package com.hhf.alaska.spring.boot.starter.zuul.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author huang hong fei
 * @date 2019/6/14
 * @description
 **/
@SpringBootApplication @EnableZuulProxy
public class ZuulDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulDemoApplication.class);
    }
}
