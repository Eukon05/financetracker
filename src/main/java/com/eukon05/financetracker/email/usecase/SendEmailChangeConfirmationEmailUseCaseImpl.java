package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SendEmailChangeConfirmationEmailUseCaseImpl implements SendEmailChangeConfirmationEmailUseCase {

    private final JavaMailSender mailSender;
    private static final String CONFIRMATION_MAIL_SUBJECT = "Confirm email change";
    private static final String CONFIRMATION_MAIL_TEXT = "Hi %s, we've received a request to change your email to: %s\nIf you want to proceed with this change, please visit the link below:\n%s";
    private static final String CONFIRMATION_LINK = "%s/confirm-email-change?token=%s";


    @Override
    public void sendEmailChangeConfirmationEmail(String username, String email, String newEmail, String rootUrl, String tokenId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(CONFIRMATION_MAIL_SUBJECT);
        message.setText(String.format(CONFIRMATION_MAIL_TEXT, username, newEmail, String.format(CONFIRMATION_LINK, rootUrl, tokenId)));

        mailSender.send(message);
    }
}
