package com.yinghua.job.trigger;

import com.yinghua.job.job.WXScheduleJob;
import org.quartz.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

@Configuration
public class JobTriggerUtil {


    public void executeSimpleTrigger(QuartzJobBean jobClass){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(jobClass.getClass());

        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        // 执行间隔时间
        simpleTriggerFactoryBean.setRepeatInterval(5000);
        // 重复执行次数
        simpleTriggerFactoryBean.setRepeatCount(3);
    }

    public SchedulerFactoryBean executeCronTrigger(Class<? extends Job> jobClass,String core){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(jobClass);


        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//        cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
        cronTriggerFactoryBean.setCronExpression(core);

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        schedulerFactoryBean.start();

        return schedulerFactoryBean;
    }
}
