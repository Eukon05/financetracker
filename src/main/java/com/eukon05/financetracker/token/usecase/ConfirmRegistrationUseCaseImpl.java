package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenRepository;
import com.eukon05.financetracker.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class ConfirmRegistrationUseCaseImpl implements ConfirmRegistrationUseCase {

    private final GetTokenByIdUseCase getTokenByIdUseCase;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public void confirmRegistration(String id) {
        Token token = getTokenByIdUseCase.getTokenById(id, TokenType.CONFIRM_REGISTRATION);

        token.getUser().setEnabled(true);
        tokenRepository.delete(token);
    }

}
