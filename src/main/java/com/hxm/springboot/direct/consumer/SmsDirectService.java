package com.hxm.springboot.direct.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */

@Component
@RabbitListener(queues = {"sms.direct.queue"})
public class SmsDirectService {

    @RabbitHandler
    public void smsReceive(String message){
        System.out.println("sms------------->:"+message);
    }
}
