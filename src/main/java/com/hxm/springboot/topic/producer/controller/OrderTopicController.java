package com.hxm.springboot.topic.producer.controller;

import com.hxm.springboot.topic.producer.service.OrderTopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: linhao
 */
@RestController
public class OrderTopicController {

    @Resource
    private OrderTopicService orderTopicService;


    @GetMapping("/topic/order")
    public void makeOrder() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderTopicService.makeOrder(userId, productId, num);
        }
    }
}
