package com.eukon05.financetracker.email.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailFacade {

    private final SendRegistrationEmailUseCase sendRegistrationEmailUseCase;

    public void sendRegistrationEmail(String name, String email, String rootUrl, String tokenId) {
        sendRegistrationEmailUseCase.sendRegistrationEmail(name, email, rootUrl, tokenId);
    }

}
