package com.fsn.kafkaretries.message.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fsn.kafkaretries.message.Message;
import com.fsn.kafkaretries.message.serviceprovider.MessageServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final MessageServiceProvider serviceProvider;

    @Autowired
    public MessageController(MessageServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @PostMapping
    public Message sendMessage(@RequestBody JsonNode request) {
        return serviceProvider.sendMessage(request);
    }
}
