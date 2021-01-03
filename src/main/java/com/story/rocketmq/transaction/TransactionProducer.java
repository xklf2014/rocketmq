package com.story.rocketmq.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author story
 * @CreateTIme 2020/12/27
 **/
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("tp1");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        producer.setTransactionListener(new TransactionListener() {
            public LocalTransactionState executeLocalTransaction(Message msg, Object o) {
                System.out.println("====executeLocalTransaction====");
                System.out.println("msg "+new String(msg.getBody()));
                System.out.println("transaction id "+msg.getTransactionId());
                return LocalTransactionState.UNKNOW;
            }

            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("====checkLocalTransaction====");
                System.out.println("msg "+new String(msg.getBody()));
                System.out.println("transaction id "+msg.getTransactionId());
                return LocalTransactionState.UNKNOW;
            }
        });

        Message msg = new Message("myTopic01","TransactionProducer".getBytes());

        final TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(msg, null);
        System.out.println("transactionSendResult"+transactionSendResult);
    }
}
