package com.roc.admin.backend.timer.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/11/14
 */
@Component
public class StartAppListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ClusterScheduler scheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("-------执行StartApplicationListener--------");
        try {
            scheduler.run(true);
        } catch (SchedulerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
