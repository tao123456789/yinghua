package com.yinghua.mq.config;

import com.yinghua.mq.constant.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //MQ启动，默认开启的几个队列
    //EmailToAdmin发送管理员邮件信息
    @Bean
    public Queue EmailToAdmin() {
        return new Queue(Constant.MQ_QUEUES_EMAIL_SEND_TO_ADMIN); //配置一个名称为"EmailToAdmin"的Queue队列
    }

    @Bean
    public Queue EmailToUser() {
        return new Queue(Constant.MQ_QUEUES_EMAIL_SEND_TO_USERS); //配置一个名称为"EmailToAdmin"的Queue队列
    }
}
