package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SendRegistrationEmailUseCaseImpl implements SendRegistrationEmailUseCase {

    private final JavaMailSender mailSender;
    private static final String REGISTRATION_MAIL_SUBJECT = "Confirm your email";
    private static final String REGISTRATION_MAIL_TEXT = "Hi %s, please visit the link below to confirm your email address and activate your account: \n%s";
    private static final String REGISTRATION_LINK = "%s/confirm-registration?token=%s";

    @Override
    public void sendRegistrationEmail(String username, String email, String rootUrl, String tokenId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(REGISTRATION_MAIL_SUBJECT);
        message.setText(String.format(REGISTRATION_MAIL_TEXT, username, String.format(REGISTRATION_LINK, rootUrl, tokenId)));

        mailSender.send(message);
    }
}
