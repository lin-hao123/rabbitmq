package com.hxm.springboot.fanout.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;
import sun.awt.SunHints;

import javax.xml.crypto.KeySelector;

/**
 * @Author: linhao
 */
@Component
@RabbitListener(queues = {"weixin.fanout.queue"})
public class WeichatService {

    @RabbitHandler
    public void weichatReceive(String message){
        System.out.println("weixing------------>"+message);
    }
}
