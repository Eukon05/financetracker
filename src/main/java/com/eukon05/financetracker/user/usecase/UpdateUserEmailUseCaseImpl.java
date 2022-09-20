package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.UserRepository;
import com.eukon05.financetracker.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateUserEmailUseCaseImpl implements UpdateUserEmailUseCase {

    private final EmailFacade emailFacade;
    private final UserRepository repository;

    @Override
    @Transactional
    public void updateUserEmail(String username, String newEmail, String rootUrl) {
        User user = repository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        Token token = new Token(TokenType.CONFIRM_EMAIL_CHANGE);
        token.setValue(newEmail);
        user.getTokens().add(token);

        emailFacade.sendEmailChangeConfirmationEmail(username, user.getEmail(), newEmail, rootUrl, token.getId());
    }
}