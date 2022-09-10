package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenRepository;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.token.exception.InvalidTokenException;
import com.eukon05.financetracker.token.exception.TokenExpiredException;
import com.eukon05.financetracker.token.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class ConfirmEmailChangeUseCaseImpl implements ConfirmEmailChangeUseCase {

    private final TokenRepository repository;


    @Override
    @Transactional
    public void confirmEmailChange(String id) {
        Token token = repository.findById(id).orElseThrow(TokenNotFoundException::new);

        if (token.getExpiresAt().isBefore(Instant.now())) {
            repository.delete(token);
            throw new TokenExpiredException();
        }

        if (!token.getTokenType().equals(TokenType.CONFIRM_EMAIL_CHANGE)) {
            throw new InvalidTokenException();
        }

        token.getUser().setEmail(token.getValue());
        repository.delete(token);
    }
}
