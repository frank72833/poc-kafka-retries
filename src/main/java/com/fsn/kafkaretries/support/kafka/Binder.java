package com.fsn.kafkaretries.support.kafka;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Binder {
    SubscribableChannel in();
    MessageChannel out();
}
