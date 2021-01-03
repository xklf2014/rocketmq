package com.story.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

/**
 * @Author story
 * @CreateTIme 2020/12/26
 * 消息发送
 **/
public class ProducerSendMsgWithTag {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("p3");

        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Message msg = new Message("myTopic01","Tag-01","key1","hello my tag".getBytes());
        producer.send(msg);

        producer.shutdown();
    }
}
