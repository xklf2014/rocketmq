package com.story.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Author story
 * @CreateTIme 2020/12/27
 **/
public class AsynProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("p2");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Message msg = new Message("myTopic01","asyn message".getBytes());
        producer.send(msg, new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
                System.out.println("sendResult: "+sendResult);
            }

            public void onException(Throwable throwable) {
                throwable.printStackTrace();
            }
        });


    }
}
