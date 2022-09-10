package com.eukon05.financetracker.email.usecase;

interface SendEmailChangeConfirmationEmailUseCase {

    void sendEmailChangeConfirmationEmail(String username, String email, String newEmail, String rootUrl, String tokenId);

}
