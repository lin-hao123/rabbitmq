package com.hxm.springboot.direct.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: linhao
 */
@Component
@RabbitListener(queues = {"weixin.direct.queue"})
public class WeichatDirectService {

    @RabbitHandler
    public void weichatReceive(String message){
        System.out.println("weixing------------>"+message);
    }
}
