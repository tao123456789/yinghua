package com.yinghua.core.domain.bo;

import java.io.Serializable;

/**
 * wxaddress
 * @author 
 */
public class Wxaddress implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private String address;

    private String method;

    private String times;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
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

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getMethod () {
        return method;
    }

    public void setMethod (String method) {
        this.method = method;
    }

    public String getTimes () {
        return times;
    }

    public void setTimes (String times) {
        this.times = times;
    }

    public String getRemarks () {
        return remarks;
    }

    public void setRemarks (String remarks) {
        this.remarks = remarks;
    }

    public static long getSerialVersionUID () {
        return serialVersionUID;
    }
}