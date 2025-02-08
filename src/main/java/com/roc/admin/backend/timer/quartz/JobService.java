package com.roc.admin.backend.timer.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

@Slf4j
//@Service
public class JobService {

    @Autowired
    private Scheduler scheduler;


    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称(建议唯一)
     * @param jobGroupName 任务组名
     * @param cron         时间表达式 （如：0/5 * * * * ? ）
     * @param jobData      参数
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroupName, String cron, Map jobData, Date endTime)
            throws SchedulerException {
        // 创建jobDetail实例，绑定Job实现类
        // 指明job的名称，所在组的名称，以及绑定job类
        // 任务名称和组构成任务key
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        // 设置job参数
        if (jobData != null && !jobData.isEmpty()) {
            jobDetail.getJobDataMap().putAll(jobData);
        }
        // 定义调度触发规则
        // 使用cornTrigger规则
        // 触发器key
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).startNow().build();
        if (endTime != null) {
            trigger.getTriggerBuilder().endAt(endTime);
        }
        // 把作业和触发器注册到任务调度中
        scheduler.scheduleJob(jobDetail, trigger);
    }


    public Trigger getTrigger(String jobName, String jobGroupName) throws SchedulerException {
        return scheduler.getTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
    }

    /**
     * 删除任务一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            log.error("Quartz delete job failed", e);
        }
    }

    /**
     * 暂停一个job
     */
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("Quartz pause job failed", e);
        }
    }

    /**
     * 恢复一个job
     */
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("Quartz resume job failed", e);
        }
    }

    /**
     * 立即执行一个job
     */
    public void runJobNow(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("Quartz run job failed", e);
        }
    }

    /**
     * 修改 一个job的 时间表达式
     *
     * @param jobName
     * @param jobGroupName
     * @param cron
     */
    public void updateJob(String jobName, String jobGroupName, String cron, Map jobData) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            } else {
                // 这里其实可以删掉在添加即可，懒得写了
                throw new RuntimeException("格式转换错误");
            }
            // 设置job参数
            if (jobData != null && !jobData.isEmpty()) {
                trigger.getJobDataMap().putAll(jobData);
            }
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("Quartz update job failed", e);
        }
    }
}