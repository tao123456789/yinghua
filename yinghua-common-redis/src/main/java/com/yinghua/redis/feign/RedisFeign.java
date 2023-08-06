package com.yinghua.redis.feign;

import com.yinghua.redis.domain.RedisPO;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/redis")
public interface RedisFeign {
    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @PostMapping("hasKey")
    Boolean hasKey(String key);

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    @PostMapping("getCacheObject")
    String getCacheObject(@RequestBody String key);

    /**
     *
     * @param redisPO
     * @param <T>
     */
    @PostMapping("setCacheObject")
     <T> void setCacheObject(@RequestBody RedisPO redisPO);
}
