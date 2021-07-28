package com.hxm.simple;

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
            //5.声明队列queue存储消息
            /*
             *  如果队列不存在，则会创建
             *  Rabbitmq不允许创建两个相同的队列名称，否则会报错。
             *
             *  @params1： queue 队列的名称
             *  @params2： durable 队列是否持久化, rabbitmq宕机，自动删除队列
             *  @params3： exclusive 是否排他，即是否私有的，如果为true,会对当前队列加锁，其他的通道不能访问，并且连接自动关闭
             *  @params4： autoDelete 是否自动删除，当最后一个消费者断开连接之后是否自动删除队列。
             *  @params5： arguments 可以设置队列附加参数，设置队列的有效期，消息的最大长度，队列的消息生命周期等等。
             * */
            String queueName = "queue1";
            channel.queueDeclare(queueName,false,false,true,null);
            //6.准备发送的消息内容
            String message = "hello, linhao.";
            //7.发送消息给中间件rabbitmq-server
            /**
             * @params1: 交换机exchange
             * @params2: 队列名称/routing
             * @params3: 属性配置
             * @params4: 发送消息的内容
             */
            channel.basicPublish("",queueName,null,message.getBytes());
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
