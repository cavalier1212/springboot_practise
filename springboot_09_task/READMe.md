# Task 排程

## Spring 內置簡單排程

### 週期間隔
```java
Timer timer = new Timer();
TimerTask task = new TimerTask(){
    @Override
    public void run() {
        System.out.println("timer task run...");
    }
};
timer.schedule(task, 0, 2000);
```
* task：要執行的任務。  
* 0：延遲時間（以毫秒為單位）。0意味著立即開始執行。  
* 2000：任務執行之間的時間間隔（也是以毫秒為單位）。在這個例子中，任務每隔2秒執行一次。  

### 指定日期

```java
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;

TimerTask task = new TimerTask() {
    @Override
    public void run() {
        System.out.println("指定時間的任務執行了");
    }
};

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date date = sdf.parse("2023-12-25 15:30:00"); // 以你想要的日期和時間替換
Timer timer = new Timer();
timer.schedule(task, date);
```
### 指定開始時間與間隔

```java
TimerTask task = new TimerTask() {
    @Override
    public void run() {
        System.out.println("指定時間的任務執行了");
    }
};

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date startDate = sdf.parse("2023-12-25 15:30:00"); // 以你想要的日期和時間替換
Timer timer = new Timer();
long period = 2 * 60 * 60 * 1000; // 週期時間，這裡設置為2小時
timer.schedule(task, startDate, period);
```

## Spring Boot 整合 quartz

> spring boot 啟動後會自己開始運行

* 創建具體Task

```java
// 需繼承 QuartzJobBean
public class MyQuartz extends QuartzJobBean {
    // 具體任務 實作 executeInternal
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // context set data
        System.out.println("quartz task run....");
    }
}
```

* config 自訂義class

```java
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail printDetail(){
        // storeDurably 持久化 預設true
        return JobBuilder.newJob(MyQuartz.class).storeDurably().build();
    } 
    
    @Bean
    public Trigger printTJobTrigger(){
        // Cron表達式 設定週期時間
        // 每分鐘的每5秒執行
        ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        // 綁定對應的工作明細
        return TriggerBuilder.newTrigger().forJob(printDetail()).withSchedule(scheduleBuilder).build();
    }
}
```

## Spring Schedule

* 啟動類 加 `@EnableScheduling`
```java
@SpringBootApplication
@EnableScheduling
public class Springboot09TaskApplication {
	// ...
}

```

* 具體任務

```java
@Component
public class SpringTask {
    // 每秒
    @Scheduled(cron = "0/1 * * * * ?")
    public void task(){
        System.out.println("spring task run...");
    }
}
```

* config

```yaml
spring:
    task:
        scheduling:
            # 任务调度线程池大小 默认 1
            pool:
                size: 1
            # 调度线程名称前缀 默认 scheduling-
            thread-name-prefix: spring_task_ 
            shutdown:
                # 線程池关闭时等待所有任务完成
                await-termination: false
                # 调度線程关闭前最大等待时间，确保最后一定关闭
                await-termination-period: 10s
```

## Cron表達式的結構

### Cron表達式的結構
```Text
(秒 分 鐘 小時 月份中的日期 月份 星期中的日期 年份(可選))
```
### 各字段解釋

1. 秒（0-59）：
   * *：任何秒值（每秒）
   * 5：第5秒
   * 0/15：從第0秒開始，每15秒
  
2. 分鐘（0-59）：
   * *：任何分鐘值（每分鐘）
   * 30：第30分
   * 0/10：從第0分開始，每10分鐘
1. 小時（0-23）：
   * *：任何小時值（每小時）
   * 14：下午2點（24小時制）
   * 0/6：從午夜開始，每6小時
 
1. 月份中的日期（1-31）：
   * *：月份中的任何日期
   * 15：月份的第15天
   * 1/5：從月份的第一天開始，每5天
1. 月份（1-12 或 JAN-DEC）：
   * *：任何月份
   * 6 或 JUN：六月
   * 
1. 星期中的日期（0-7 其中 0 和 7 都是代表星期日 或 SUN-SAT）：
   * *：星期的任何一天
   * 3 或 WED：星期三
   * 1-5：星期一至星期五

1. 年份（可選，例如1970-2099）：
   * *：任何年份
   * 2023：2023年

### 例子

* 每天上午8:30執行
```text
0 30 8 * * ?
```

* 每個工作日（星期一至星期五）上午9:15執行：
```text
0 15 9 ? * MON-FRI
```

* 每月1號和15號的下午2:30執行：
```text
0 30 14 1,15 * ?
```

* 每年1月1日午夜12點執行：
```text
0 0 0 1 1 ?
```
* 注意事項
> * Cron表達式是基於伺服器的時區的，因此設定任務時需要考慮時區差異。  
> * 不是所有的Cron表達式解析器都支援相同的特性和範圍。這尤其在不同的排程系統間變得明顯（比如Unix/Linux的cron和Quartz排程器）。  
> * 理解Cron表達式的基礎和如何構造它們對於設定自動化任務非常重要。這些表達式提供了強大的靈活性來精確定義何時執行特定的作業或命令。