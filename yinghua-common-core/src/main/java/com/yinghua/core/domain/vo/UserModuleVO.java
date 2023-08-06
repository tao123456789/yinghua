package com.yinghua.core.domain.vo;

public class UserModuleVO {
    int id;
    int userid;
    String username;
    String realname;
    int moduleid;
    String module_name;
    String module_url;
    String img_url;
    String description;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getUserid () {
        return userid;
    }

    public void setUserid (int userid) {
        this.userid = userid;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getRealname () {
        return realname;
    }

    public void setRealname (String realname) {
        this.realname = realname;
    }

    public int getModuleid () {
        return moduleid;
    }

    public void setModuleid (int moduleid) {
        this.moduleid = moduleid;
    }

    public String getModule_name () {
        return module_name;
    }

    public void setModule_name (String module_name) {
        this.module_name = module_name;
    }

    public String getModule_url () {
        return module_url;
    }

    public void setModule_url (String module_url) {
        this.module_url = module_url;
    }

    public String getImg_url () {
        return img_url;
    }

    public void setImg_url (String img_url) {
        this.img_url = img_url;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}
