package com.yinghua.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YinghuaAuthApplication {
    public static void main (String[] args) {
        SpringApplication.run(YinghuaAuthApplication.class,args);
    }
}
