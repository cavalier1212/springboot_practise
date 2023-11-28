package com.example.springboot_10_mq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;

public class MessageListener {
    
    // 自動消費消息
    @JmsListener(destination = "order.queue.id")
    @SendTo("other.order.queue.id")// 接收當前方法的返回值，加入下一個佇列
    public String receive(String id){
        System.out.println("已完成短信發送業務, id = "+id);
        return "new : "+id;
    }
}
