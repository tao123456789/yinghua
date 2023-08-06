package com.yinghua.core.domain.bo;

public class SettingBO {
    int id;
    int type;
    String name;
    String code;
    String remark;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getType () {
        return type;
    }

    public void setType (int type) {
        this.type = type;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }
}
