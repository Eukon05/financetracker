package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SendPasswordResetConfirmationEmailUseCaseImpl implements SendPasswordResetConfirmationEmailUseCase {

    private final JavaMailSender mailSender;
    private static final String CONFIRMATION_MAIL_SUBJECT = "Confirm password reset";
    private static final String CONFIRMATION_MAIL_TEXT = "Hi %s, we've received a request to reset your password.\nAfter reset, your password will be changed to: %s\nIf you want to proceed with this change, please visit the link below:\n%s";
    private static final String CONFIRMATION_LINK = "%s/confirm-password-change?token=%s";

    @Override
    public void sendRegistrationEmail(String username, String email, String newPassword, String rootUrl, String tokenId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(CONFIRMATION_MAIL_SUBJECT);
        message.setText(String.format(CONFIRMATION_MAIL_TEXT, username, newPassword, String.format(CONFIRMATION_LINK, rootUrl, tokenId)));

        mailSender.send(message);
    }

}
