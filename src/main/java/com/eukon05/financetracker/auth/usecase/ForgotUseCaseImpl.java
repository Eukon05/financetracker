package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ForgotUseCaseImpl implements ForgotUseCase {

    private final UserFacade userFacade;
    private final EmailFacade emailFacade;

    @Override
    @Transactional
    public void forgot(String email, String rootUrl) {
        User user = userFacade.getUserByEmailOrThrow(email);
        Token token = new Token(TokenType.CONFIRM_PASSWORD_CHANGE);
        token.setValue(RandomString.make(8));
        user.getTokens().add(token);

        emailFacade.sendPasswordResetConfirmationEmail(user.getUsername(), email, token.getValue(), rootUrl, token.getId());
    }
}
