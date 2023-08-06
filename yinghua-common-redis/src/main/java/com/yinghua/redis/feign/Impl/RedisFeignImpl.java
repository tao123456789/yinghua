package com.yinghua.redis.feign.Impl;

import com.yinghua.redis.domain.RedisPO;
import com.yinghua.redis.feign.RedisFeign;
import com.yinghua.redis.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisFeignImpl implements RedisFeign {
    @Resource
    RedisService redisService;

    @Override
    public Boolean hasKey (String key) {
        return redisService.hasKey(key);
    }

    @Override
    public String getCacheObject (String key) {
        return redisService.getCacheObject(key);
    }

    @Override
    public <T> void setCacheObject (RedisPO redisPO) {
        redisService.setCacheObject(redisPO.getKey(),redisPO.getValue(),redisPO.getTime(),redisPO.getTimeUnit());
    }
}
