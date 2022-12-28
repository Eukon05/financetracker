package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateUserPasswordUseCaseImpl implements UpdateUserPasswordUseCase {

    private final EmailFacade emailFacade;
    private final GetUserUseCase getUserUseCase;

    @Override
    @Transactional
    public void updateUserPassword(String username, String newPassword, String rootUrl) {
        User user = getUserUseCase.getUserByUsernameOrThrow(username);

        Token token = new Token(TokenType.CONFIRM_PASSWORD_CHANGE);
        token.setValue(newPassword);
        user.getTokens().add(token);

        emailFacade.sendPasswordResetConfirmationEmail(username, user.getEmail(), newPassword, rootUrl, token.getId());
    }

}
