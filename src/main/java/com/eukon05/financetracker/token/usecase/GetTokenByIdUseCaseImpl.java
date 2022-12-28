package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenRepository;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.token.exception.InvalidTokenException;
import com.eukon05.financetracker.token.exception.TokenExpiredException;
import com.eukon05.financetracker.token.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class GetTokenByIdUseCaseImpl implements GetTokenByIdUseCase {

    private final TokenRepository repository;

    @Override
    public Token getTokenById(String id, TokenType type) {
        Token token = repository.findById(id).orElseThrow(TokenNotFoundException::new);

        if (token.getExpiresAt().isBefore(Instant.now())) {
            repository.delete(token);
            throw new TokenExpiredException();
        }

        if (!token.getTokenType().equals(type)) {
            throw new InvalidTokenException();
        }

        return token;
    }
}
