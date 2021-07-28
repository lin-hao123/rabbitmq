package com.hxm.springboot.fanout.producer.controller;

import com.hxm.springboot.fanout.producer.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: linhao
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping("/order")
    public void makeOrder() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderService.makeOrder(userId, productId, num);
        }
    }
}
