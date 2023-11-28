package service.impl.rabbitmq.topic.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 寫兩個監聽器聽同一個佇列，行為會是監聽器輪詢(輪流處理)
@Component
public class MessageListener {
    
    @RabbitListener( queues = "topic_queue" )// 要聽的佇列名稱
    public void receive(String id){
        System.out.println("已完成短信發送業務(rabbit topic), id = "+id);
    }

    @RabbitListener( queues = "topic_queue2" )// 要聽的佇列名稱
    public void receive2(String id){
        System.out.println("已完成短信發送業務(rabbit topic two), id = "+id);
    }
}
