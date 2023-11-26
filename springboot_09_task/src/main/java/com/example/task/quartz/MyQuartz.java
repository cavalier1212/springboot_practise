package com.example.task.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyQuartz extends QuartzJobBean {
    // 具體任務
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // context set data
        System.out.println("quartz task run....");
    }
}
