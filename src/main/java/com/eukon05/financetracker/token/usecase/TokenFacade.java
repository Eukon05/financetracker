package com.eukon05.financetracker.token.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenFacade {

    private final ConfirmRegistrationUseCase confirmRegistrationUseCase;

    public void confirmRegistration(String id) {
        confirmRegistrationUseCase.confirmRegistration(id);
    }

}
