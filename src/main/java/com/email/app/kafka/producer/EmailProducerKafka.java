package com.email.app.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.email.app.model.Email;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class EmailProducerKafka {

	@Autowired
	private KafkaTemplate<String, Email> kafkaTemplate;

	@Value("${emailapp.kafkatopic}")
	private String topic;

	public void send(Email email) throws JsonProcessingException {
		Message<Email> message = MessageBuilder.withPayload(email).setHeader(KafkaHeaders.TOPIC, topic).build();
		kafkaTemplate.send(message);
	}

}
