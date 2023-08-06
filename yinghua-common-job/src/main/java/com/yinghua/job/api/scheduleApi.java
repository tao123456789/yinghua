package com.yinghua.job.api;

import com.yinghua.job.schedule.WXClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/schedule")
public class scheduleApi {
    @Resource
    WXClient wxClient;

    @GetMapping("test")
    public String create() throws Exception {
        wxClient.WXScheduleService();
        return "c成功";
    }
}
