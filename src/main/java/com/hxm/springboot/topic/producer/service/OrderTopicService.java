package com.hxm.springboot.topic.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: linhao
 */
@Service
public class OrderTopicService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 1: 定义交换机
    private String exchangeName = "topic_order_exchange";
    // 2: 路由key
    /**
     *         "email.#"
     *         "*.sms.#"
     *         "#.weixin.#"
     */
    private String routeKey = "email.sms";
    public void makeOrder(Long userId, Long productId, int num) {
        // 1： 模拟用户下单
        String orderNumer = UUID.randomUUID().toString();
        System.out.println("用户 " + userId + ",订单编号是：" + orderNumer);
        // 发送订单信息给RabbitMQ fanout
        rabbitTemplate.convertAndSend(exchangeName, routeKey, orderNumer);
    }
}
