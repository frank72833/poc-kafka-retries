package com.fsn.kafkaretries.message.support.kafka.binders;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageBinders {
    String MESSAGE = "message";

    @Input(MESSAGE)
    SubscribableChannel messageIn();

    @Output(MESSAGE)
    MessageChannel messageOut();
}
