package com.eukon05.financetracker.email.usecase;

interface SendPasswordResetConfirmationEmailUseCase {

    void sendRegistrationEmail(String username, String email, String newPassword, String rootUrl, String tokenId);

}
