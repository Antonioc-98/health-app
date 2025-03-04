package com.app.HealthApp.healthapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetToken(String email, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("antonio.praksa@gmail.com");
        message.setTo(email.trim());
        message.setSubject("Zahtjev za promjenom lozinke");
        message.setText("Za promjenu lozinke, koristite sljedeći token:\n"
                + "Token: " + resetToken + "\n\n"
                + "Ako niste poslali ovaj zahtjev, molim vas ignorirajte ovaj email.");

        mailSender.send(message);
    }
}