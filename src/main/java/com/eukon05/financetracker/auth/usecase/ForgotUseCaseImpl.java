package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ForgotUseCaseImpl implements ForgotUseCase {

    @Override
    @Transactional
    public Token forgot(User user) {
        Token token = new Token(TokenType.CONFIRM_PASSWORD_CHANGE);
        token.setValue(RandomStringUtils.randomAlphanumeric(8));
        user.getTokens().add(token);

        return token;
    }
}
