package com.story.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

/**
 * @Author story
 * @CreateTIme 2020/12/26
 * 消息发送
 **/
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("p1");

        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Message msg = new Message("myTopic01","hello world-1".getBytes());
        producer.send(msg);
        //单向消息
        //producer.sendOneway(msg);

        ArrayList<Message> messages = new ArrayList<Message>();
        messages.add(new Message("myTopic01","message1".getBytes()));
        messages.add(new Message("myTopic01","message2".getBytes()));
        messages.add(new Message("myTopic01","message3".getBytes()));

        producer.send(messages);

        //Thread.sleep(2000);
        producer.shutdown();
    }
}
