package com.fsn.kafkaretries.message.support.kafka.listeners;

import com.fsn.kafkaretries.message.Message;
import com.fsn.kafkaretries.message.support.kafka.binders.MessageBinders;
import com.fsn.kafkaretries.support.kafka.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @Value("${spring.cloud.stream.bindings.message.consumer.maxAttempts}")
    private int maxAttempts;

    @StreamListener(target = MessageBinders.MESSAGE, condition = "headers['eventType']=='INSERTED'")
    public void handleMessagesInserted(@Payload Event<Message> message, @Header("deliveryAttempt") int deliveryAttempt) {
        log.info("handleMessagesInserted: [message: " + message + "] [deliveryAttempt: "+ deliveryAttempt +"] [maxAttempts: "+ maxAttempts +"]");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @StreamListener(target = MessageBinders.MESSAGE, condition = "headers['eventType']=='STATUS_CHANGE'")
    public void handleMessagesStatusChange(@Payload Event<Message> message, @Header("deliveryAttempt") int deliveryAttempt) {
        log.info("handleMessagesStatusChange: [message: " + message + "] [deliveryAttempt: "+ deliveryAttempt +"] [maxAttempts: "+ maxAttempts +"]");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @StreamListener(target = MessageBinders.MESSAGE, condition = "headers['eventType']==null")
    public void handleMessages(@Payload Event<Message> message, @Header("deliveryAttempt") int deliveryAttempt) {
        log.info("handleMessages: [message: " + message + "] [deliveryAttempt: "+ deliveryAttempt +"] [maxAttempts: "+ maxAttempts +"]");

        throw new RuntimeException("error processing message");
    }
}
