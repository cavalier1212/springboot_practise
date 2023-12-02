package service.impl.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    
    @KafkaListener(topics = "topic_kafka")
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("已完成短信發送業務(kafka), id = "+record.value());

    }
}
