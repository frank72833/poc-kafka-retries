package com.fsn.kafkaretries.message.support.kafka.binders;

import com.fsn.kafkaretries.support.kafka.Binder;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageBinders extends Binder {
    String MESSAGE = "message";

    @Input(MESSAGE)
    SubscribableChannel in();

    @Output(MESSAGE)
    MessageChannel out();
}
