package com.example.task;

import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTaskApp {
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("timer task run...");
            }
        };
        timer.schedule(task, 0, 2000);
        // TimerTask task = new TimerTask() {
        //     @Override
        //     public void run() {
        //         System.out.println("指定時間的任務執行了");
        //     }
        // };

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date startDate = sdf.parse("2023-12-25 15:30:00"); // 以你想要的日期和時間替換
        // Timer timer = new Timer();
        // long period = 2 * 60 * 60 * 1000; // 週期時間，這裡設置為2小時
        // timer.schedule(task, startDate, period);
    }
}
