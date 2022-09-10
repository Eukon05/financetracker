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

@RequiredArgsConstructor
@Service
class ConfirmRegistrationUseCaseImpl implements ConfirmRegistrationUseCase {

    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public void confirmRegistration(String id) {
        Token token = tokenRepository.findById(id).orElseThrow(TokenNotFoundException::new);

        if (token.getExpiresAt().isBefore(Instant.now())) {
            tokenRepository.delete(token);
            throw new TokenExpiredException();
        }

        if (!token.getTokenType().equals(TokenType.CONFIRM_REGISTRATION)) {
            throw new InvalidTokenException();
        }

        token.getUser().setEnabled(true);
        tokenRepository.delete(token);
    }

}
