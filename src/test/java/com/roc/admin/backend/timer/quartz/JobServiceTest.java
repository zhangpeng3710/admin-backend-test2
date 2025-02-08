package com.roc.admin.backend.timer.quartz;

import org.junit.jupiter.api.Test;
import org.quartz.CalendarIntervalTrigger;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.Date;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/16
 */
class JobServiceTest {
    @Test
    public void test() throws ParseException, SchedulerException {
        String cron = "0/10 * * * * ?";
        CronExpression cronExpression = new CronExpression(cron);
        Date nextValidTimeAfter = cronExpression.getNextValidTimeAfter(new Date());
        System.out.println("nextValidTimeAfter=" + nextValidTimeAfter);
        nextValidTimeAfter = cronExpression.getNextValidTimeAfter(nextValidTimeAfter);
        System.out.println("nextValidTimeAfter=" + nextValidTimeAfter);
        nextValidTimeAfter = cronExpression.getNextValidTimeAfter(nextValidTimeAfter);
        System.out.println("nextValidTimeAfter=" + nextValidTimeAfter);

        CalendarIntervalTrigger calendarIntervalTrigger =
                newTrigger().withIdentity("trigger3", "group1")
                        .withSchedule(calendarIntervalSchedule().withIntervalInSeconds(10)).build();

        Date nextFireTime = calendarIntervalTrigger.getFireTimeAfter(new Date());
        System.out.println("nextFireTime=" + nextFireTime);
        nextFireTime = calendarIntervalTrigger.getFireTimeAfter(nextFireTime);
        System.out.println("nextFireTime=" + nextFireTime);
        nextFireTime = calendarIntervalTrigger.getFireTimeAfter(nextFireTime);
        System.out.println("nextFireTime=" + nextFireTime);
        nextFireTime = calendarIntervalTrigger.getFireTimeAfter(nextFireTime);
        System.out.println("nextFireTime=" + nextFireTime);

    }
}