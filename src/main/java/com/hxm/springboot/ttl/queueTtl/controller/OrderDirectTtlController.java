package com.hxm.springboot.ttl.queueTtl.controller;

import com.hxm.springboot.ttl.queueTtl.service.OrderDirectTtlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: linhao
 */
@RestController
public class OrderDirectTtlController {

    @Resource
    private OrderDirectTtlService orderDirectTtlService;


    @GetMapping("/direct/ttl/order")
    public void makeOrder() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderDirectTtlService.makeOrder(userId, productId, num);
        }
    }
}
