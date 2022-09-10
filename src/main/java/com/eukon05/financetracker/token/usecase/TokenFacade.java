package com.eukon05.financetracker.token.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenFacade {

    private final ConfirmRegistrationUseCase confirmRegistrationUseCase;
    private final ConfirmEmailChangeUseCase confirmEmailChangeUseCase;

    public void confirmRegistration(String id) {
        confirmRegistrationUseCase.confirmRegistration(id);
    }

    public void confirmEmailChange(String id) {
        confirmEmailChangeUseCase.confirmEmailChange(id);
    }

}
