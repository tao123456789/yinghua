package com.yinghua.mq.domain.po;

import java.io.Serializable;

public class EmailPO implements Serializable {
    String title;
    String content;
    String subject;
    String toEmail;

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public String getSubject () {
        return subject;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    public String getToEmail () {
        return toEmail;
    }

    public void setToEmail (String toEmail) {
        this.toEmail = toEmail;
    }
}
