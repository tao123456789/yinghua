package com.yinghua.core.domain.bo;

public class SubscriberBO {
    private int id;
    private String code;
    private String name;
    private String remark;
    private String status;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }
}
