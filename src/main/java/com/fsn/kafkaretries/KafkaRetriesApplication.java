package com.fsn.kafkaretries;

import com.fsn.kafkaretries.message.support.kafka.binders.MessageBinders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({MessageBinders.class})
public class KafkaRetriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaRetriesApplication.class, args);
	}

}
