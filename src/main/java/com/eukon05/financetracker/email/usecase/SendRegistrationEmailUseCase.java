package com.eukon05.financetracker.email.usecase;

interface SendRegistrationEmailUseCase {

    void sendRegistrationEmail(String username, String email, String rootUrl, String tokenId);

}
