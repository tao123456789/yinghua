package com.yinghua.mq.api.Impl;

import com.yinghua.mq.service.EmailSendToMQService;
import com.yinghua.mq.api.EmailMQApi;
import com.yinghua.mq.constant.Constant;
import com.yinghua.mq.domain.po.EmailPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmailMQApiImpl implements EmailMQApi {

    @Resource
    EmailSendToMQService emailSendToMQService;


    @Override
    public void emailSendToUsers (EmailPO emailPO, List<String> userList){
        emailSendToMQService.SendEmailToMQ(Constant.MQ_QUEUES_EMAIL_SEND_TO_USERS,emailPO,userList);
    }

    @Override
    public void emailSendToAdmin (EmailPO emailPO){
        emailSendToMQService.SendEmailToMQ(Constant.MQ_QUEUES_EMAIL_SEND_TO_ADMIN,emailPO);
    }
}
