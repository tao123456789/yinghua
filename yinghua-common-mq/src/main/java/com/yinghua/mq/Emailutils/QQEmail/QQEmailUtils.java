package com.yinghua.mq.Emailutils.QQEmail;

import com.yinghua.core.domain.bo.SettingBO;
import com.yinghua.mq.domain.po.EmailPO;
import com.yinghua.mq.feign.CommonCoreFeignService;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

@Component
public class QQEmailUtils {
    @Resource
    CommonCoreFeignService commonCoreFeignService;

    public void SendToByQQEmail(String Content,String Subject,String ToEmail) throws Exception {
        SettingBO settingBO=new SettingBO();
        settingBO.setType(1);
        settingBO.setName("QQAccount");
        String QQAccount=commonCoreFeignService.getSetting(settingBO).get(0).getCode();
        settingBO.setName("QQSendAuth");
        String QQSendAuth=commonCoreFeignService.getSetting(settingBO).get(0).getCode();
        System.out.println("QQ账号QQAccount："+QQAccount+"，授权码QQSendAuth："+QQSendAuth);
        try {
            Properties properties = new Properties();
            properties.put("mail.transport.protocol", "smtp");// 连接协议
            properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
            properties.put("mail.smtp.port", 465);// 端口号
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接 ---一般都使用
            properties.put("mail.debug", "true");//设置是否显示debug信息 true 会在控制台显示相关信息
            //得到回话对象
            Session session = Session.getInstance(properties);
            // 获取邮件对象
            Message message = new MimeMessage(session);
            //
            message.setContent(Content,"text/html;charset=UTF-8");
            //设置发件人邮箱地址
            message.setFrom(new InternetAddress(QQAccount));
            //设置收件人地址
            message.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress(ToEmail)});
            //设置邮件标题
            message.setSubject(Subject);
            //设置邮件内容
            message.setText(Content);

            //得到邮差对象
            Transport transport = session.getTransport();
            //连接自己的邮箱账户
            transport.connect(QQAccount, QQSendAuth);//密码为刚才得到的授权码
            transport.sendMessage(message, message.getAllRecipients());
        }catch (Exception e){
            System.out.println("发给 "+ToEmail+" 的邮件发送失败： "+e);
            throw new Exception("发送邮件失败！！！");
        }
    }


    public static class QQMailClass{

        String QQAccount;
        String QQSendAuth;

        public void getQQAccount() {
            SettingBO settingBO=new SettingBO();
            settingBO.setType(1);
            settingBO.setName("QQAccount");
        }
        public void getQQSendAuth() {
            SettingBO settingBO=new SettingBO();
            settingBO.setType(1);
            settingBO.setName("QQSendAuth");
        }
    }

    public Session setMimeMessage(){
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");//设置是否显示debug信息 true 会在控制台显示相关信息
        //得到回话对象
        Session session = Session.getInstance(properties);
        return session;
    }

    private static MimeMessage getMimeMessage(Session session,String Subject,String Content,String ToEmail) throws Exception {
        QQMailClass qqMailClass=new QQMailClass();
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(qqMailClass.QQAccount));
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(ToEmail));
        //4.设置邮件主题
        msg.setSubject(Subject, "UTF-8");

        //设置邮件正文
        //msg.setContent("测试发邮件！", "text/html;charset=UTF-8");

        // 5. 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();

        // 读取本地文件
        //DataHandler dh = new DataHandler(new FileDataSource(new File("D:\\image\\fj.png")));
        // 将图片数据添加到"节点"
        //image.setDataHandler(dh);

        // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
        image.setContentID("mailTestPic");
        // 6. 创建文本"节点"
        MimeBodyPart text = new MimeBodyPart();

        // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        //text.setContent("这是一封带文字,图片和附件的邮件<br/><img src='cid:mailTestPic'/></a>", "text/html;charset=UTF-8");

        text.setContent(Content, "text/html;charset=UTF-8");
        // 7. （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related"); // 关联关系

        // 8. 将 文本+图片 的混合"节点"封装成一个普通"节点"
        // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mailTestPic 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);
        // 9. 创建附件"节点"
        MimeBodyPart attachment = new MimeBodyPart();
        // 读取本地文件
        DataHandler dh2 = new DataHandler(new FileDataSource(new File("D:\\宝贝.docx")));
        // 将附件数据添加到"节点"
        attachment.setDataHandler(dh2);
        // 设置附件的文件名（需要编码）
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment); // 如果有多个附件，可以创建多个多次添加
        mm.setSubType("mixed");  // 混合关系

        // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        msg.setContent(mm);

        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
        return msg;
    }
}
