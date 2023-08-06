package com.yinghua.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
public class YingHuaRedisApplication {
    public static void main (String[] args) {
        SpringApplication.run(YingHuaRedisApplication.class,args);
    }
}
