package com.story.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/12/26
 * 消息发送
 **/
public class ProducerSelector {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rp1");

        producer.setNamesrvAddr("localhost:9876");
        producer.start();



        Message msg = new Message("myTopic01","MessageQueueSelector".getBytes());
        producer.send(msg, new MessageQueueSelector() {
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                MessageQueue queue = list.get((Integer) o);

                return queue;
            }
        },0,2000);

        producer.shutdown();
    }
}
