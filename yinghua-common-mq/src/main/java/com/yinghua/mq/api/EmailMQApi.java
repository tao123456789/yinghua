package com.yinghua.mq.api;

import com.yinghua.mq.domain.po.EmailPO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yinghua-common-mq")
public interface EmailMQApi {
    @PostMapping("/emailSendToUsers")
    void emailSendToUsers(@RequestBody EmailPO emailPO, @RequestParam("userList") List<String> userList) throws Exception;

    @PostMapping("/emailToAdmin")
    void emailSendToAdmin(@RequestBody EmailPO emailPO) throws Exception;
}
