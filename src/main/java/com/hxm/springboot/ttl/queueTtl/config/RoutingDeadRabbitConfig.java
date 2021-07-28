package com.hxm.springboot.ttl.queueTtl.config;

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
public class RoutingDeadRabbitConfig {


    //声明队列
    @Bean
    public Queue emailDirectDeadQueue(){
        return new Queue("email.direct.dead.queue",true);
    }

    //声明交换机
    @Bean
    public DirectExchange directDeadOrderExchange(){
        return new DirectExchange("direct_dead_order_exchange",true,false);
    }

    //队列绑定到交换机
    @Bean
    public Binding emailQueueDirectDeadExchangeBinding(){
        return BindingBuilder.bind(emailDirectDeadQueue()).to(directDeadOrderExchange()).with("dead");
    }
}
