package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenRepository;
import com.eukon05.financetracker.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ConfirmPasswordChangeUseCaseImpl implements ConfirmPasswordChangeUseCase {

    private final GetTokenByIdUseCase getTokenByIdUseCase;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void confirmPasswordChange(String id) {
        Token token = getTokenByIdUseCase.getTokenById(id, TokenType.CONFIRM_PASSWORD_CHANGE);

        token.getUser().setPassword(encoder.encode(token.getValue()));
        tokenRepository.delete(token);
    }

}
