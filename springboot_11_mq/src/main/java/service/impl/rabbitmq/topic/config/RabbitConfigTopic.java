package service.impl.rabbitmq.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigTopic {
    @Bean
    public Queue topicQueue(){
        // (name, true, false, false);預設
        // durable：是否持久化，默认false
        // exclusive：是否当前连接专用，默认false，连接关闭后队列即被删除
        // autoDelete：是否自动删除，当生严者或消费者不再使用此队列，自动删除
        return new Queue("topic_queue");
    }

    @Bean
    public Queue topicQueue2(){
        // (name, true, false, false);預設
        return new Queue("topic_queue2");
    }


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_exchange");
    }

    @Bean
    public Binding bindingTopic(){
        // routingKey 可以寫成模糊比對
        // topic.#  : # -> 通配符，代表任意多個單詞，但是最後一個單詞不能是.
        // topic.*.id : * -> 單一單詞模糊匹配 
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.order.id");  // routing key
    } 

    // 不同消息佇列 可以用同一個消息交換機exchange處理
    @Bean
    public Binding bindingTopic2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic_routing2");  // routing key
    } 
}
