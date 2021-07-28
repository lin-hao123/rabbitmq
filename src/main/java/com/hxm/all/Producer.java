package com.hxm.all;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**简单队列生产者
 * @Author: linhao
 */
public class Producer {
    public static void main(String[] args){
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.设置连接属性
        connectionFactory.setHost("haoxiangma.top");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            //3.从连接工厂中获取连接
            connection = connectionFactory.newConnection("生产者");
            //4.从连接中获取通道channel
            channel = connection.createChannel();
            //5.声明交换机
            String exchangeName = "direct_order_exchange";
            String exchangeType = "direct";
            channel.exchangeDeclare(exchangeName,exchangeType,true);

            //6.声明队列queue存储消息
            channel.queueDeclare("orderQueue1",true,false,true,null);
            channel.queueDeclare("orderQueue2",true,false,true,null);
            channel.queueDeclare("orderQueue3",true,false,true,null);

            //7.绑定队列和交换机的关系
            channel.queueBind("orderQueue1",exchangeName,"order_routing_key");
            channel.queueBind("orderQueue2",exchangeName,"order_routing_key");
            channel.queueBind("orderQueue3",exchangeName,"detail_routing_key");

            //6.准备发送的消息内容
            String message = "hello, linhao.";
            //7.发送消息给中间件rabbitmq-server
            /**
             * @params1: 交换机exchange
             * @params2: 队列名称/routing
             * @params3: 属性配置
             * @params4: 发送消息的内容
             */
            channel.basicPublish(exchangeName,"detail_routing_key",null,message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //8.释放连接关闭通道
            if(channel!=null && channel.isOpen()){
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null && connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
