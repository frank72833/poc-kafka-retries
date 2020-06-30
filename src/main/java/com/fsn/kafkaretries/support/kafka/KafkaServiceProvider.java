package com.fsn.kafkaretries.support.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceProvider<T extends Binder> {

    private final T binders;

    @Autowired
    public KafkaServiceProvider(T binders) {
        this.binders = binders;
    }

    public boolean sendMessage(Event<?> message) {
        return binders.out().send(MessageBuilder.withPayload(message).build());
    }
}
