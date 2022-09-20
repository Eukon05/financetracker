package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailFacade {

    private final SendRegistrationEmailUseCase sendRegistrationEmailUseCase;
    private final SendEmailChangeConfirmationEmailUseCase sendEmailChangeConfirmationEmailUseCase;
    private final SendPasswordResetConfirmationEmailUseCase sendPasswordResetConfirmationEmailUseCase;

    public void sendRegistrationEmail(String username, String email, String rootUrl, String tokenId) {
        sendRegistrationEmailUseCase.sendRegistrationEmail(username, email, rootUrl, tokenId);
    }

    public void sendEmailChangeConfirmationEmail(String username, String email, String newEmail, String rootUrl, String tokenId) {
        sendEmailChangeConfirmationEmailUseCase.sendEmailChangeConfirmationEmail(username, email, newEmail, rootUrl, tokenId);
    }

    public void sendPasswordResetConfirmationEmail(String username, String email, String newPassword, String rootUrl, String tokenId) {
        sendPasswordResetConfirmationEmailUseCase.sendRegistrationEmail(username, email, newPassword, rootUrl, tokenId);
    }

}
