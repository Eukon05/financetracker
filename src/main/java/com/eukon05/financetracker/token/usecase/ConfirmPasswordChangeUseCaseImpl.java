package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenRepository;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.token.exception.InvalidTokenException;
import com.eukon05.financetracker.token.exception.TokenExpiredException;
import com.eukon05.financetracker.token.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class ConfirmPasswordChangeUseCaseImpl implements ConfirmPasswordChangeUseCase {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void confirmPasswordChange(String id) {
        Token token = tokenRepository.findById(id).orElseThrow(TokenNotFoundException::new);

        if (token.getExpiresAt().isBefore(Instant.now())) {
            tokenRepository.delete(token);
            throw new TokenExpiredException();
        }

        if (!token.getTokenType().equals(TokenType.CONFIRM_PASSWORD_CHANGE)) {
            throw new InvalidTokenException();
        }

        token.getUser().setPassword(encoder.encode(token.getValue()));
        tokenRepository.delete(token);
    }

}
