package com.eukon05.financetracker.token;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
class TokenCleanerImpl implements TokenCleaner {

    private final TokenRepository tokenRepository;
    private final UserFacade userFacade;
    private static final String MESSAGE_DELETED_TOKENS = "Deleted expired tokens from the database.";
    private static final String MESSAGE_DELETED_USER = "Deleted user {}. Reason: Account has not been been activated.";

    @Override
    @Scheduled(cron = "0 0 * ? * *")
    public void cleanExpiredTokens() {
        List<Token> expiredTokens = tokenRepository.findTokensByExpiresAtBefore(Instant.now());
        List<User> inactiveUsers = expiredTokens
                .stream()
                .filter(token -> TokenType.CONFIRM_REGISTRATION.equals(token.getTokenType()))
                .map(Token::getUser)
                .toList();

        tokenRepository.deleteAll(expiredTokens);
        log.info(MESSAGE_DELETED_TOKENS);
        userFacade.deleteMultipleUsers(inactiveUsers);
        inactiveUsers.forEach(user -> log.info(MESSAGE_DELETED_USER, user.getUsername()));
    }

}
