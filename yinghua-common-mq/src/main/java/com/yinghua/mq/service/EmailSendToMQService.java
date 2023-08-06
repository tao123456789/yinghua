package com.yinghua.mq.service;

import com.yinghua.mq.domain.po.EmailPO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EmailSendToMQService {
    @Resource
    AmqpTemplate rabbitTemplate;

    public void SendEmailToMQ(String Queues, EmailPO emailPO) {
        System.out.println("向"+Queues+"队列发送数据："+emailPO.toString());
        this.rabbitTemplate.convertAndSend(Queues, emailPO);
    }

    public void SendEmailToMQ(String Queues, EmailPO emailPO, List<String> userList) {
        System.out.println("向"+Queues+"队列发送数据："+emailPO.toString());
        for (int i = 0; i < userList.size(); i++) {
            emailPO.setToEmail(userList.get(i));
            this.rabbitTemplate.convertAndSend(Queues, emailPO);
        }
    }
}
