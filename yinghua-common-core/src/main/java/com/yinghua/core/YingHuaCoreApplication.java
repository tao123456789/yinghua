package com.yinghua.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.yinghua.core.mapper")
@EnableFeignClients
public class YingHuaCoreApplication {
    public static void main (String[] args) {
        SpringApplication.run(YingHuaCoreApplication.class,args);
    }
}
