package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.eukon05.financetracker.email.EmailConstants.*;

@Service
@RequiredArgsConstructor
public class EmailFacade {
    private final SendEmailUseCase sendEmailUseCase;

    public void sendRegistrationEmail(String username, String email, String rootUrl, String tokenId) {
        sendEmailUseCase.sendEmail(email,
                REGISTRATION_MAIL_SUBJECT,
                String.format(REGISTRATION_MAIL_TEXT, username, String.format(REGISTRATION_LINK, rootUrl, tokenId)));
    }

    public void sendEmailChangeConfirmationEmail(String username, String email, String newEmail, String rootUrl, String tokenId) {
        sendEmailUseCase.sendEmail(email,
                EMAIL_CHANGE_CONFIRMATION_MAIL_SUBJECT,
                String.format(EMAIL_CHANGE_CONFIRMATION_MAIL_TEXT, username, newEmail, String.format(EMAIL_CHANGE_CONFIRMATION_MAIL_LINK, rootUrl, tokenId)));
    }

    public void sendPasswordResetConfirmationEmail(String username, String email, String newPassword, String rootUrl, String tokenId) {
        sendEmailUseCase.sendEmail(email,
                PASSWORD_RESET_CONFIRMATION_MAIL_SUBJECT,
                String.format(PASSWORD_RESET_CONFIRMATION_MAIL_TEXT, username, newPassword, String.format(PASSWORD_RESET_CONFIRMATION_LINK, rootUrl, tokenId)));
    }

}
