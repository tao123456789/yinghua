package com.yinghua.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YingHuaCommonMqApplication {
    public static void main (String[] args) {
        SpringApplication.run(YingHuaCommonMqApplication.class,args);
    }
}
