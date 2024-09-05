package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        try {
            emailService.sendEmail();
            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending email.";
        }
    }
}



