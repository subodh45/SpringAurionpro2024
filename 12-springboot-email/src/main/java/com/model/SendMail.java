package com.model;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    public static void main(String[] args) {

        // Recipient's email ID and your email details
        String to = "subodhmagar45@gmail.com";  // Change to the recipient's email
        String from = "subodhmagar7823@gmail.com";     // Your Gmail
        String host = "smtp.gmail.com";           // Gmail SMTP server

        // SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Authenticate using your email and the App Password you generated
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("subodhmagar7823@gmail.com", "jtnc ksbr xaob cvgm"); // Your Gmail and App Password
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Test Email from Java after a lot of hardwork");

            // Set the actual message content
            message.setText("Hello, this is a test email sent from my Java project!");

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

