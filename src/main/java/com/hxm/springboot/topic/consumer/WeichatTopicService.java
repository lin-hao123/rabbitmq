package com.hxm.springboot.topic.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "weixin.topic.queue",autoDelete = "false",durable = "true"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "#.weixin.#"
))
public class WeichatTopicService {

    @RabbitHandler
    public void weichatReceive(String message){
        System.out.println("weixing------------>"+message);
    }
}
