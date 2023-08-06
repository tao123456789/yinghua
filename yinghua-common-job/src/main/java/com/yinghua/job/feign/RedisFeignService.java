package com.yinghua.job.feign;

import com.yinghua.redis.domain.RedisPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yinghua-common-redis")
public interface RedisFeignService {
    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @PostMapping("/common/redis/hasKey")
    Boolean hasKey(@RequestParam("key") String key);

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    @PostMapping("/common/redis/getCacheObject")
    String getCacheObject(@RequestBody String key);

    @PostMapping("/common/redis/setCacheObject")
    <T> void setCacheObject(@RequestBody RedisPO redisPO);
}
