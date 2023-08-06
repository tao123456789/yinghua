package com.yinghua.mq.enums;

public enum MQEmailFunctionCodeEnums {

    MQ_QUEUES_EMAIL_SEND_TO_ADMIN("邮件队列-发送消息给管理员","MQ_QUEUES_EMAIL_SEND_TO_ADMIN"),
    MQ_QUEUES_EMAIL_SEND_TO_USERS("邮件队列-发送消息给管理员","MQ_QUEUES_EMAIL_SEND_TO_USERS"),
    ;

    private String name;//名称
    private String code;//编码
    private String functiontype;//功能类型
    private String type;//分类

    MQEmailFunctionCodeEnums(String name,String code){
        this.name = name;
        this.code = code;
    }

    public String getName () {
        return name;
    }

    public String getCode () {
        return code;
    }
}
