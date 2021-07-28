package com.hxm.springboot.fanout.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;
import sun.awt.SunHints;

/**
 * @Author: linhao
 */

@Component
@RabbitListener(queues = {"sms.fanout.queue"})
public class SMSService {

    @RabbitHandler
    public void smsReceive(String message){
        System.out.println("message------------->:"+message);
    }
}
