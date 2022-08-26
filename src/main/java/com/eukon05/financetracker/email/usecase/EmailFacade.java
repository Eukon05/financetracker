package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailFacade {

    private final SendRegistrationEmailUseCase sendRegistrationEmailUseCase;

    public void sendRegistrationEmail(String username, String email, String rootUrl, String tokenId) {
        sendRegistrationEmailUseCase.sendRegistrationEmail(username, email, rootUrl, tokenId);
    }

}
