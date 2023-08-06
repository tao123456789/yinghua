package com.yinghua.mq.consume;

import com.yinghua.mq.Emailutils.QQEmail.QQEmailUtils;
import com.yinghua.mq.domain.po.EmailPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QQEmailService {
    @Resource
    QQEmailUtils qqEmailUtils;
    public void SendToAdmin(EmailPO emailPO) throws Exception {
        String emailAddress="2413629661@qq.com";
        emailPO.setToEmail(emailAddress);
        qqEmailUtils.SendToByQQEmail(emailPO.getContent(),emailPO.getSubject(), emailPO.getToEmail());
    }
}
