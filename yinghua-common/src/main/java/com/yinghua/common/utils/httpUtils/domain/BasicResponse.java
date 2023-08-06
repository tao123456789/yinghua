package com.yinghua.common.utils.httpUtils.domain;

import com.yinghua.common.basicenum.ResultCode;

import java.io.Serializable;

public class BasicResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    //状态码
    private Integer code=200;

    //响应消息
    private String msg ="处理成功";

    //响应数据
    private T data;

    public BasicResponse (ResultCode resultCode, String msg) {
        this.code=resultCode.getCode();
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void Result(ResultCode resultCode, T data) {
        this.code=resultCode.getCode();
        this.msg=resultCode.getMsg();
        this.data=data;
    }
    public void Result(Integer code, String msg, T data) {
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
}
