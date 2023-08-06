package com.yinghua.mq.consume;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.yinghua.mq.constant.Constant;
import com.yinghua.mq.domain.po.EmailPO;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Constant.MQ_QUEUES_EMAIL_SEND_TO_ADMIN)
public class LocalConsume {
    @Autowired
    QQEmailService qqEmailService;

    @RabbitHandler
    public void SendEmailMQ(EmailPO emailPO){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("发送管理员信息 : " + emailPO.getContent());
        try {
            qqEmailService.SendToAdmin(emailPO);
        }catch (Exception e){
            System.out.println("发送管理员信息失败："+e);
        }
        System.out.println("---------------------------------------------------------------------");
    }

}
