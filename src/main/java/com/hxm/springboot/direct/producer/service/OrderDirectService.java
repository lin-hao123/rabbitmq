package com.hxm.springboot.direct.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: linhao
 */
@Service
public class OrderDirectService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 1: 定义交换机
    private String exchangeName = "direct_order_exchange";
    // 2: 路由key
    private String smsRouteKey = "sms";
    private String emailRouteKey = "email";
    public void makeOrder(Long userId, Long productId, int num) {
        // 1： 模拟用户下单
        String orderNumer = UUID.randomUUID().toString();
        // 2: 根据商品id productId 去查询商品的库存
        // int numstore = productSerivce.getProductNum(productId);
        // 3:判断库存是否充足
        // if(num >  numstore ){ return  "商品库存不足..."; }
        // 4: 下单逻辑
        // orderService.saveOrder(order);
        // 5: 下单成功要扣减库存
        // 6: 下单完成以后
        System.out.println("用户 " + userId + ",订单编号是：" + orderNumer);
        // 发送订单信息给RabbitMQ fanout
        rabbitTemplate.convertAndSend(exchangeName, smsRouteKey, orderNumer);
        rabbitTemplate.convertAndSend(exchangeName, emailRouteKey, orderNumer);
    }
}
