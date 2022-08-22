package com.eukon05.financetracker.service.token;

import com.eukon05.financetracker.service.token.usecase.confirmRegistration.ConfirmRegistrationUseCase;
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
