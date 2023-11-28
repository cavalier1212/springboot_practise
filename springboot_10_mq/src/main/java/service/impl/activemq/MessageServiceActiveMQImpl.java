package service.impl.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import service.impl.MessageService;

public class MessageServiceActiveMQImpl implements MessageService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待發送短信的訂單已納入處理對列. id = "+ id);
        // 設定佇列名稱,放入佇列
        jmsMessagingTemplate.convertAndSend("order.queue.id",id);
    }

    @Override
    public String doMessage() {
        // 佇列名稱,取得(手動)
        String id = jmsMessagingTemplate.receiveAndConvert("order.queue.id",String.class);
        System.out.println("已完成短信發送業務, id = "+id);
        return id;
    }
    
}
