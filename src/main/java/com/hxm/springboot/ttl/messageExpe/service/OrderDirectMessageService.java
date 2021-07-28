package com.hxm.springboot.ttl.messageExpe.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: linhao
 */
@Service
public class OrderDirectMessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 1: 定义交换机
    private String exchangeName = "direct_message_order_exchange";
    // 2: 路由key
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

        /*
        过期时间分为两种
        第一种方法是通过队列属性设置，队列中所有消息都有相同的过期时间  ttl。
        第二种方法是对消息进行单独设置，每条消息TTL可以不同。
        当同时指定了 queue 和 message 的 TTL 值，则两者中较小的那个才会起作用。

        第一种消息过期后可以存入死信息队列
        第二种消息过期后就直接扔掉了
         */

        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        // 发送订单信息给RabbitMQ fanout
        rabbitTemplate.convertAndSend(exchangeName, emailRouteKey, orderNumer,messagePostProcessor);
    }
}
