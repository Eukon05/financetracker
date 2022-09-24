package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenRepository;
import com.eukon05.financetracker.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ConfirmEmailChangeUseCaseImpl implements ConfirmEmailChangeUseCase {

    private final GetTokenByIdUseCase getTokenByIdUseCase;
    private final TokenRepository repository;


    @Override
    @Transactional
    public void confirmEmailChange(String id) {
        Token token = getTokenByIdUseCase.getTokenById(id, TokenType.CONFIRM_EMAIL_CHANGE);

        token.getUser().setEmail(token.getValue());
        repository.delete(token);
    }
}
