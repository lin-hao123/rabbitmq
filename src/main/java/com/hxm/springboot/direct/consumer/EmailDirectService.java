package com.hxm.springboot.direct.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */
@Component
@RabbitListener(queues = {"email.direct.queue"})
public class EmailDirectService {

    @RabbitHandler
    public void messageReceive(String message){
        System.out.println("email------------->"+message);

    }
}
