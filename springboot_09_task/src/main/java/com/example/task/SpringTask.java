package com.example.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {
    
    @Scheduled(cron = "0/1 * * * * ?")
    public void task(){
        System.out.println(Thread.currentThread().getName()+" :spring task run...");
    }
}
