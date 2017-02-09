package com.fly.location;

import com.aliyun.openservices.ons.api.*;

import java.util.Properties;

/**
 * Created by John on 2016/12/16.
 */
public class SendAliMessage {
    private Producer producer;
    private void init(){
        if (producer == null){
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.ProducerId, "PID_WEIWT_LOCATION");// 您在MQ控制台创建的Producer ID
            properties.put(PropertyKeyConst.AccessKey,"LTAImMDTHhNAV6Ge");// 鉴权用AccessKey，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.SecretKey, "PligF8lACHYUUQDrIxGCAQiQUI93MK");// 鉴权用SecretKey，在阿里云服务器管理控制台创建
            producer = ONSFactory.createProducer(properties);
        }
        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可
        if (!producer.isStarted()){
            producer.start();
        }
    }

    public static SendAliMessage getInstance(){
        SendAliMessage sendAliMessage = new SendAliMessage();
        sendAliMessage.init();
        return sendAliMessage;
    }


    public void sendMessage(String topic, String tag, String messageBody) {
        try {
            Message msg = new Message( //
                    // Message Topic
                    topic,
                    // Message Tag,
                    // 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                    tag,
                    // Message Body
                    // 任何二进制形式的数据， MQ不做任何干预，
                    // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                    messageBody.getBytes("UTF-8"));
            // 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过MQ控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("18501635120");
            // 发送消息，只要不抛异常就是成功
            // 打印Message ID，以便用于消息发送状态查询
            SendResult sendResult = producer.send(msg);
            System.out.println("Send Message success. Message ID is: " + sendResult.getMessageId());
        }catch (Exception e){
            e.printStackTrace();
        }

        // 在应用退出前，可以销毁Producer对象
        // 注意：如果不销毁也没有问题
//        producer.shutdown();
    }

    public static void main(String[] args) {
        SendAliMessage sendAliMessage = new SendAliMessage();
        sendAliMessage.sendMessage("location", "test", "测试");
    }
}
