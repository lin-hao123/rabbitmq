package com.hxm.springboot.ttl.messageExpe.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: linhao
 */
@Configuration
public class RoutingMessageRabbitConfig {

    //声明队列
    @Bean
    public Queue emailDirectMessageQueue(){
        return new Queue("email.direct.message.queue",true,false,false);
    }

    //声明交换机
    @Bean
    public DirectExchange directMessageOrderExchange(){
        return new DirectExchange("direct_message_order_exchange",true,false);
    }

    //队列绑定到交换机
    @Bean
    public Binding emailQueueDirectMessageExchangeBinding(){
        return BindingBuilder.bind(emailDirectMessageQueue()).to(directMessageOrderExchange()).with("email");
    }
}
