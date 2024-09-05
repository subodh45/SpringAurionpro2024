package com.model.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendEmailWithAttachment(String to, String subject, String text, MultipartFile file)
			throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();

		// Using the true flag to indicate multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);

		// Add attachment
		if (file != null && !file.isEmpty()) {
			// Convert MultipartFile to DataSource
			ByteArrayDataSource dataSource = new ByteArrayDataSource(file.getInputStream(), file.getContentType());
			helper.addAttachment(file.getOriginalFilename(), dataSource);
		}

		emailSender.send(message);
	}
}
