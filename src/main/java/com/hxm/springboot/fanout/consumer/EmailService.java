package com.hxm.springboot.fanout.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */
@Component
@RabbitListener(queues = {"email.fanout.queue"})
public class EmailService {

    @RabbitHandler
    public void messageReceive(String message){
        System.out.println("email------------->"+message);

    }
}
