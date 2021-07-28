package com.hxm.springboot.direct.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: linhao
 */
@Configuration
public class RoutingRabbitConfig {

    //声明队列
    @Bean
    public Queue emailDirectQueue(){
        return new Queue("email.direct.queue",true);
    }

    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue",true);
    }

    @Bean
    public Queue weixinDirectQueue(){
        return new Queue("weixin.direct.queue",true);
    }

    //声明交换机
    @Bean
    public DirectExchange directOrderExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }

    //队列绑定到交换机
    @Bean
    public Binding emailQueueDirectExchangeBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directOrderExchange()).with("email");
    }

    @Bean
    public Binding smsQueueDirectExchangeBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directOrderExchange()).with("sms");
    }

    @Bean
    public Binding weixinQueueDirectExchangeBinding(){
        return BindingBuilder.bind(weixinDirectQueue()).to(directOrderExchange()).with("weixin");
    }



}
