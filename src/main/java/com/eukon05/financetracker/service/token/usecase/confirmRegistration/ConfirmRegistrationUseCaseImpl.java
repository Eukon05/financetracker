package com.eukon05.financetracker.service.token.usecase.confirmRegistration;

import com.eukon05.financetracker.domain.Token;
import com.eukon05.financetracker.exception.TokenExpiredException;
import com.eukon05.financetracker.exception.TokenNotFoundException;
import com.eukon05.financetracker.repository.TokenRepository;
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

        token.getUser().setEnabled(true);
        tokenRepository.delete(token);
    }

}
