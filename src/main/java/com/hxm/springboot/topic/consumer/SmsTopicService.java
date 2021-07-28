package com.hxm.springboot.topic.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue",autoDelete = "false",durable = "true"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "*.sms.#"
))
public class SmsTopicService {

    @RabbitHandler
    public void smsReceive(String message){
        System.out.println("message------------->:"+message);
    }
}
