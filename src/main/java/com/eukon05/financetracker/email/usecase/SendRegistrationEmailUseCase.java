package com.eukon05.financetracker.email.usecase;

interface SendRegistrationEmailUseCase {

    void sendRegistrationEmail(String name, String email, String rootUrl, String tokenId);

}
