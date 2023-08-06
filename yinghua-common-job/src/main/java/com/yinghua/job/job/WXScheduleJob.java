package com.yinghua.job.job;

import com.yinghua.job.schedule.WXClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WXScheduleJob extends QuartzJobBean {

    @Resource
    WXClient wxClient;

    @Override
    protected void executeInternal (JobExecutionContext context) throws JobExecutionException {
        System.out.println("============微信定时任务==============");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("MyQuartzJob  executeInternal ----" + sdf.format(new Date()));
        try {
            wxClient.WXScheduleService();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
