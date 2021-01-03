package com.story.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

/**
 * @Author story
 * @CreateTIme 2020/12/26
 * 消息发送
 **/
public class ReTryProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rp1");

        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        //重试次数
        producer.setRetryTimesWhenSendAsyncFailed(2);
        //是否向其他broker发送消息
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);

        Message msg = new Message("myTopic01","hello world-1".getBytes());
        producer.send(msg);

        producer.shutdown();
    }
}
