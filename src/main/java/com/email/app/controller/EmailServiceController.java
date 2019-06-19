package com.email.app.controller;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email.app.kafka.producer.EmailProducerKafka;
import com.email.app.model.Email;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class EmailServiceController {
	
	@Autowired
	private EmailProducerKafka emailProducerKafka;
	
	@RequestMapping("sendEmailAsync")
	public String sendEmailAsync(@RequestParam("mailTo") String mailTo,
							@RequestParam("mailSubject") Optional<String> mailSubject,
							@RequestParam("mailBody") Optional<String> mailBody,
							@RequestParam("attachment") Optional<String> attachmentURI) throws MessagingException {

		Email email = new Email(mailTo,
								mailSubject.isPresent() ? mailSubject.get() : "",
								mailBody.isPresent() ? mailBody.get() : "",
								attachmentURI.isPresent() ? attachmentURI.get() : "");
		try {
			emailProducerKafka.send(email);
		} catch (MailException | JsonProcessingException mailException) {
			System.out.println(mailException);
		}
		return "Your mail has been put into queue to send asynchronously.";
	}

}
