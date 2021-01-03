package com.story.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;

/**
 * @Author story
 * @CreateTIme 2020/12/27
 **/
public class BatchProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("P3");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        ArrayList<Message> messages = new ArrayList<Message>();
        for (int i =0;i<100;i++){
            Message msg = new Message("myTopic01", ("price:" + i).getBytes());
            msg.putUserProperty("price", String.valueOf(i));
            messages.add(msg);
        }
        producer.send(messages);
        producer.shutdown();

    }
}
