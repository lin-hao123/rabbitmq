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
public class RoutingTtlRabbitConfig {


    //声明队列
    @Bean
    public Queue emailDirectTtlQueue(){
        Map<String,Object> ttl = new HashMap<>();
        ttl.put("x-message-ttl",20000);
        ttl.put("x-dead-letter-exchange","direct_dead_order_exchange");
        ttl.put("x-dead-letter-routing-key","dead");
        ttl.put("x-max-length",6);
        return new Queue("email.direct.ttl.queue",true,false,false,ttl);
    }

    //声明交换机
    @Bean
    public DirectExchange directTtlOrderExchange(){
        return new DirectExchange("direct_ttl_order_exchange",true,false);
    }

    //队列绑定到交换机
    @Bean
    public Binding emailQueueDirectTtlExchangeBinding(){
        return BindingBuilder.bind(emailDirectTtlQueue()).to(directTtlOrderExchange()).with("email");
    }
}
