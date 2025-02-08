package com.roc.admin.backend.timer.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/16
 */
@Slf4j
@Component
public class CronScheduler {
    @Autowired
    private Scheduler scheduler;

    public void run() throws SchedulerException, InterruptedException {

        JobDetail job1 = JobBuilder.newJob(DemoJob.class)
                .withIdentity("MyJob-Enable-Concurrent", "MyGroup")
                .build();


        // a cron scheduler at every 10 seconds
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger", "MyGroup")
                .startAt(DateBuilder.evenSecondDate(new Date()))
                //.endAt(new Date(System.currentTimeMillis() + 60 * 1000))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ? *"))
                .build();


        JobKey jobKey1 = new JobKey("MyJob-Enable-Concurrent", "MyGroup");
        JobKey jobKey2 = new JobKey("MyJob-Disable-concurrent", "MyGroup");
        TriggerKey triggerKey = new TriggerKey("MyTrigger", "MyGroup");


        scheduler.scheduleJob(job1, trigger);


        log.info("start the scheduler.");
        scheduler.start();

    }
}