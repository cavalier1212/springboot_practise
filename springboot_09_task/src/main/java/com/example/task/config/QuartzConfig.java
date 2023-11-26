package com.example.task.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.task.quartz.MyQuartz;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail printDetail(){
        // storeDurably 持久化 預設true
        return JobBuilder.newJob(MyQuartz.class).storeDurably().build();
    } 
    
    @Bean
    public Trigger printTJobTrigger(){
        ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        // 綁定對應的工作明細
        return TriggerBuilder.newTrigger().forJob(printDetail()).withSchedule(scheduleBuilder).build();
    }
}
