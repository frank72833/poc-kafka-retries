package com.fsn.kafkaretries.message.serviceprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fsn.kafkaretries.message.Message;
import com.fsn.kafkaretries.message.support.kafka.binders.MessageBinders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class MessageServiceProvider {

    private final MessageBinders messageBinders;

    @Autowired
    public MessageServiceProvider(MessageBinders messageBinders) {
        this.messageBinders = messageBinders;
    }

    public Message sendMessage(JsonNode body) {
        log.info("sendMessage [body: " + body + "]");

        Message message = Message.builder()
                .id(UUID.randomUUID().toString())
                .body(body)
                .build();

        // send message with body
        boolean res = messageBinders.messageOut().send(MessageBuilder.withPayload(message).build());

        log.info("sendMessage sent! [res: "+ res + "] [message: " + message + "]");

        return message;
    }
}
