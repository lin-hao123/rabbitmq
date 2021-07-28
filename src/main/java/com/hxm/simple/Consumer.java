package com.hxm.simple;

import com.rabbitmq.client.*;

import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @Author: linhao
 */
public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("haoxiangma.top");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection("消费者");
            channel = connection.createChannel();
            channel.basicConsume("queue1", true, new DeliverCallback() {
                public void handle(String consumerTag, Delivery message) throws IOException {
                    System.out.println("收到的消息是：" + new String(message.getBody(), "UTF-8"));
                }
            }, new CancelCallback() {
                public void handle(String consumerTag) throws IOException {
                    System.out.println("消息接收失败");
                }
            });
            System.out.println("开始接收消息");
            System.in.read();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
