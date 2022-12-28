package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SendEmailUseCaseImpl implements SendEmailUseCase {

    private final JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String author;

    @Override
    public void sendEmail(String recipient, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(author);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);

    }
}
