package com.projects.app.bankApplication.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    // Send Email Method with exception handling
    public void sendSimpleMail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("smtptester29@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);

            System.out.println("Mail sent successfully to " + to);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to send email to " + to, e);
        }
    }
}
