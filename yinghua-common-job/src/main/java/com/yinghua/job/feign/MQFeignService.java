package com.yinghua.job.feign;

import com.yinghua.mq.domain.po.EmailPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "yinghua-common-mq")
public interface MQFeignService {
    @PostMapping("/yinghua-common-mq/emailSendToUsers")
    void emailSendToUsers(@RequestBody EmailPO emailPO, @RequestParam("userList") List<String> userList);

    @PostMapping("/yinghua-common-mq/emailToAdmin")
    void emailSendToAdmin(@RequestBody EmailPO emailPO);
}
