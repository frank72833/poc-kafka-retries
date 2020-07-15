package com.fsn.kafkaretries.message.support.kafka;

import com.fsn.kafkaretries.message.support.kafka.binders.MessageBinders;
import com.fsn.kafkaretries.support.kafka.KafkaServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageKafkaServiceProvider extends KafkaServiceProvider<MessageBinders> {

    @Autowired
    public MessageKafkaServiceProvider(MessageBinders messageBinders) {
        super(messageBinders);
    }
}
