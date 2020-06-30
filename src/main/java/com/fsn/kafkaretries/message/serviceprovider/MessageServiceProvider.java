package com.fsn.kafkaretries.message.serviceprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fsn.kafkaretries.message.Message;
import com.fsn.kafkaretries.message.support.kafka.binders.MessageBinders;
import com.fsn.kafkaretries.support.kafka.Event;
import com.fsn.kafkaretries.support.kafka.KafkaServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class MessageServiceProvider {

    private final KafkaServiceProvider<MessageBinders> kafkaServiceProvider;

    @Autowired
    public MessageServiceProvider(KafkaServiceProvider<MessageBinders> kafkaServiceProvider) {
        this.kafkaServiceProvider = kafkaServiceProvider;
    }

    public Message sendMessage(JsonNode body) {
        log.info("sendMessage [body: " + body + "]");

        Message message = Message.builder()
                .id(UUID.randomUUID().toString())
                .body(body)
                .build();

        Event<Message> event = new Event<>();
        event.setEventName(Event.EventName.INSERTED);
        event.setItem(message);

        kafkaServiceProvider.sendMessage(event);

        return message;
    }
}
