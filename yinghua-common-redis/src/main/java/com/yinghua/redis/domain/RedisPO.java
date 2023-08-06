package com.yinghua.redis.domain;

import java.util.concurrent.TimeUnit;

public class RedisPO {
    /**
     *          *
     *          * @param key      键
     *          * @param value    值
     *          * @param time     时间 time要大于0 如果time小于等于0 将设置无限期
     *          * @param timeUnit 时间单位
     */
    String key;
    Object value;
    long time;
    TimeUnit timeUnit;

    public String getKey () {
        return key;
    }

    public void setKey (String key) {
        this.key = key;
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object value) {
        this.value = value;
    }

    public long getTime () {
        return time;
    }

    public void setTime (long time) {
        this.time = time;
    }

    public TimeUnit getTimeUnit () {
        return timeUnit;
    }

    public void setTimeUnit (TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
