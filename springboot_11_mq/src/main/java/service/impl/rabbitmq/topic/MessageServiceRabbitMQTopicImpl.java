package service.impl.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.impl.MessageService;

@Service
public class MessageServiceRabbitMQTopicImpl implements MessageService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待發送短信的訂單已納入處理對列(rabbitmq topic). id = "+ id);
        amqpTemplate.convertAndSend("topic_exchange", "topic.order.id" ,id);
    }

    @Override
    public String doMessage() {
        return null;
    }
    
}
