package service.impl.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

// @Configuration
public class RabbitConfigDirect {
    @Bean
    public Queue directQueue(){
        // (name, true, false, false);預設
        // durable：是否持久化，默认false
        // exclusive：是否当前连接专用，默认false，连接关闭后队列即被删除
        // autoDelete：是否自动删除，当生严者或消费者不再使用此队列，自动删除
        return new Queue("direct_queue");
    }

    @Bean
    public Queue directQueue2(){
        // (name, true, false, false);預設
        return new Queue("direct_queue2");
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_exchange");
    }

    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct_routing");  // routing key
    } 

    // 不同消息佇列 可以用同一個消息交換機exchange處理
    @Bean
    public Binding bindingDirect2(){
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("direct_routing2");  // routing key
    } 
}
