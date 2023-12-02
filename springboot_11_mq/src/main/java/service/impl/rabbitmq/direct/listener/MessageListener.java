package service.impl.rabbitmq.direct.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

// 寫兩個監聽器聽同一個佇列，行為會是監聽器輪詢(輪流處理)
// @Component
public class MessageListener {
    
    @RabbitListener( queues = "direct_queue" )// 要聽的佇列名稱
    public void receive(String id){
        System.out.println("已完成短信發送業務(rabbit direct), id = "+id);
    }
}
