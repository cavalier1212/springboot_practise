package service.impl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import service.impl.MessageService;

@Service
public class MessageServicceKafkaImpl implements MessageService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待發送短信的訂單已納入處理對列. id = "+ id);
        kafkaTemplate.send("topic_kafka", id);
    }

    @Override
    public String doMessage() {
        
        return null;
    }
    
}
