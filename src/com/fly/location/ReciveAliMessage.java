package com.fly.location;

import com.aliyun.openservices.ons.api.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by John on 2016/12/16.
 */
public class ReciveAliMessage {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, "");// 您在MQ控制台创建的Consumer ID
        properties.put(PropertyKeyConst.AccessKey, "");// 鉴权用AccessKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, "");// 鉴权用SecretKey，在阿里云服务器管理控制台创建
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("location", "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
                System.out.println("Receive: " + message);
                try {
                    System.out.println("消息内容：" + new String(message.getBody(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Action.CommitMessage;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
    }
}
