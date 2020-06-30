package com.fsn.kafkaretries.support.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Event<T> {
    public enum EventName {
        INSERTED,
        STATUS_CHANGE,
        MODIFIED,
        REMOVED;
    }

    private EventName eventName;
    private String eventVersion = "1.0";
    private T item;
}

