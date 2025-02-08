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
@DisallowConcurrentExecution
public class DemoJobDisableConcurrent extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("DemoJobDisableConcurrent开始运行啦！{}", Calendar.getInstance().getTime());
    }
}
