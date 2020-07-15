package com.fsn.kafkaretries.support.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public abstract class KafkaServiceProvider<T extends Binder> {

    private final T binders;

    public KafkaServiceProvider(T binders) {
        this.binders = binders;
    }

    public boolean sendEvent(Event<?> event) {
        return binders.out().send(MessageBuilder
                .withPayload(event)
                .setHeader("eventType", event.getEventType() != null ? event.getEventType().toString() : null)
                .build());
    }
}
