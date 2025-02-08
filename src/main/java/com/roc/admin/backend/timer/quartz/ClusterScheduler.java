package com.roc.admin.backend.timer.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/16
 */
@Slf4j
//@Service
public class ClusterScheduler {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建任务调度或者集群执行任务调度
     *
     * @param bSchedulerNewJob True 创建任务调度，False 集群执行任务调度
     */
    public void run(Boolean bSchedulerNewJob) throws SchedulerException, InterruptedException {

        if (bSchedulerNewJob) {
            log.info("clear any existing job scheduler before creating new one.");
            scheduler.clear();

            JobDetail job = JobBuilder.newJob(DemoJob.class)
                    .withIdentity("MyJob", "MyGroup")
                    .requestRecovery()
                    .build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("MyTrigger", "MyGroup")
                    .startAt(DateBuilder.evenMinuteDate(new Date()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(0))
                    .build();

            log.info("start new job scheduler.");
            scheduler.scheduleJob(job, trigger);

        }
        log.info("start the scheduler.");
        scheduler.start();

        log.info("sleeping for scheduler to run job.");
        Thread.sleep(60L * 1000L);

        log.info("shutdown the scheduler gracefully.");
        scheduler.shutdown(true);
        log.info("the end.");


    }
}
