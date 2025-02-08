package com.roc.admin.backend.timer.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Calendar;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/16
 */
@Slf4j
/**
 * this marks a Job class as one that must not have multiple instances executed concurrently
 * where instance is based-upon a JobDetail definition - or in other words based upon a JobKey
 * See JobDetail definition in {@link com.roc.admin.backend.timer.quartz.ClusterScheduler}
 */
@DisallowConcurrentExecution
public class DemoJobDisableConcurrent extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("DemoJobDisableConcurrent开始运行啦！{}", Calendar.getInstance().getTime());
    }
}
