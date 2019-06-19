package com.email.app.kafka.consumer;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.email.app.model.Email;
import com.email.app.service.EmailService;

@Service
public class EmailListenerKafka {

	@Autowired
	private EmailService emailService;

	@KafkaListener(topics = "${emailapp.kafkatopic}")
	public void listen(@Payload Email email, @Headers MessageHeaders headers) {
		try {
			emailService.sendEmail(email);
		} catch (MailException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
