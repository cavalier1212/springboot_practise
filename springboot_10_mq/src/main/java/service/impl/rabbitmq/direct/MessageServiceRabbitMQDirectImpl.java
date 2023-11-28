package service.impl.rabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import service.impl.MessageService;

// @Service
public class MessageServiceRabbitMQDirectImpl implements MessageService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待發送短信的訂單已納入處理對列(rabbitmq direct). id = "+ id);
        amqpTemplate.convertAndSend("direct_exchange", "direct_routing" ,id);
    }

    @Override
    public String doMessage() {
        return null;
    }
    
}
