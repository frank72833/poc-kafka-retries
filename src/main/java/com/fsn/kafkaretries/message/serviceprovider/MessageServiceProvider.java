package com.fsn.kafkaretries.message.serviceprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fsn.kafkaretries.message.Message;
import com.fsn.kafkaretries.message.support.kafka.MessageKafkaServiceProvider;
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

    private final MessageKafkaServiceProvider messageKafkaServiceProvider;

    @Autowired
    public MessageServiceProvider(MessageKafkaServiceProvider messageKafkaServiceProvider) {
        this.messageKafkaServiceProvider = messageKafkaServiceProvider;
    }

    public Message sendMessage(JsonNode body) {
        log.info("sendMessage [body: " + body + "]");

        Message message = Message.builder()
                .id(UUID.randomUUID().toString())
                .body(body)
                .build();

        Event<Message> event = new Event<>();
        event.setItem(message);

        if (body.get("eventName") != null) {
            Event.EventType eventName = Event.EventType.valueOf(body.get("eventName").asText());
            event.setEventType(eventName);
        }

        messageKafkaServiceProvider.sendEvent(event);

        return message;
    }
}
