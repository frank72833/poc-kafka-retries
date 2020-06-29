package com.fsn.kafkaretries.message.support.kafka.listeners;

import com.fasterxml.jackson.databind.JsonNode;
import com.fsn.kafkaretries.message.Message;
import com.fsn.kafkaretries.message.support.kafka.binders.MessageBinders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @StreamListener(MessageBinders.MESSAGE)
    public void handleMessages(Message message) {
        log.info("handleMessages: [message: " + message + "]");

        JsonNode body = message.getBody();

        /*Thread.sleep(120000);*/

        boolean error = body.get("error") != null ? body.get("error").asBoolean() : false;
        if (error) {
            throw new RuntimeException("An error has occurred while receiving the message");
        }

        log.info("handleMessages completed: [message: " + message + "]");
    }
}
