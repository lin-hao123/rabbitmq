package com.hxm.springboot.direct.producer.controller;

import com.hxm.springboot.direct.producer.service.OrderDirectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: linhao
 */
@RestController
public class OrderDirectController {

    @Resource
    private OrderDirectService orderDirectService;


    @GetMapping("/direct/order")
    public void makeOrder() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderDirectService.makeOrder(userId, productId, num);
        }
    }
}
