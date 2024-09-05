package com.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.model.service.EmailService;

import jakarta.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmailWithAttachment(@RequestParam String to, @RequestParam String subject,
			@RequestParam String text, @RequestParam("file") MultipartFile file) {

		try {
			emailService.sendEmailWithAttachment(to, subject, text, file);
			return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to send email!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
