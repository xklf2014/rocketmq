package com.story.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/12/27
 **/
public class MessageOrderly {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("oc1");
        consumer.setNamesrvAddr("localhost:9876");

        consumer.subscribe("myTopic01","*");


        //设置最大开始线程数量
        //consumer.setConsumeThreadMax(1);
        //设置最小开启线程数量
        //consumer.setConsumeThreadMin(1);
        /*
        *
        *每一个queue只有一个线程（MessageListenerOrderly）
        * */
        consumer.registerMessageListener(new MessageListenerOrderly() {
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list){
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }
}
