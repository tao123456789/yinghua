package com.yinghua.job;

import com.yinghua.job.schedule.WXClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Schedule {

    @Resource
    WXClient wxClient;

    @Scheduled(fixedRate = 60000)
    public void task() {
        System.out.println("============微信定时任务==============");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("MyQuartzJob  executeInternal ----" + sdf.format(new Date()));
        try {
            wxClient.WXScheduleService();
        } catch (Exception e) {
            e.printStackTrace();
        }    }

}
