package com.hxm.springboot.topic.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "email.topic.queue",autoDelete = "false",durable = "true"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "email.#"
))
public class EmailTopicService {

    @RabbitHandler
    public void messageReceive(String message){
        System.out.println("email------------->"+message);

    }
}
