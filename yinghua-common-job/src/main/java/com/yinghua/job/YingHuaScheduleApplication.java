package com.yinghua.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value="com.yinghua.job.mapper")
@EnableScheduling
@EnableFeignClients
public class YingHuaScheduleApplication {
    public static void main (String[] args) {
        SpringApplication.run(YingHuaScheduleApplication.class,args);
    }
}
