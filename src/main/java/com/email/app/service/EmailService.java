package com.email.app.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.email.app.model.Email;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	private static int COUNTER = 0;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Retryable(value = { MailException.class, MessagingException.class }, maxAttemptsExpression = "#{${emailapp.maxRetry}}", backoff = @Backoff(2000))
	public void sendEmail(Email email) throws MailException, MessagingException {
		System.out.println(COUNTER++);
		if (email.getMailAttachmentURI() == null || email.getMailAttachmentURI().isEmpty()) {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(email.getMailTo());
			mail.setSubject(email.getMailSubject());
			mail.setText(email.getMailBody());
			javaMailSender.send(mail);
		} else {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(email.getMailTo());
			helper.setSubject(email.getMailSubject());
			helper.setText(email.getMailBody());

			ClassPathResource classPathResource = new ClassPathResource(email.getMailAttachmentURI());
			helper.addAttachment(classPathResource.getFilename(), classPathResource);
			javaMailSender.send(mimeMessage);
		}

	}

	@Recover
	public void recover(MailParseException mailParseException) {
		System.out.println("The retry has been recovered.");
	}
}
