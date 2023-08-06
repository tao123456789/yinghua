package com.yinghua.core.domain.bo;

public class ModuleBO {
    int id;
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
