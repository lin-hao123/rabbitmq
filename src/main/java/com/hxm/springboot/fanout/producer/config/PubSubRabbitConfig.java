package com.hxm.springboot.fanout.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: linhao
 */
@Configuration
public class PubSubRabbitConfig {

    //声明队列
    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue",true);
    }

    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue",true);
    }

    @Bean
    public Queue weixinQueue(){
        return new Queue("weixin.fanout.queue",true);
    }

    //声明交换机
    @Bean
    public FanoutExchange fanoutOrderExchange(){
        return new FanoutExchange("fanout_order_exchange",true,false);
    }

    //队列绑定到交换机
    @Bean
    public Binding emailQueueFanoutExchangeBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutOrderExchange());
    }

    @Bean
    public Binding smsQueueFanoutExchangeBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutOrderExchange());
    }

    @Bean
    public Binding weixinQueueFanoutExchangeBinding(){
        return BindingBuilder.bind(weixinQueue()).to(fanoutOrderExchange());
    }



}
