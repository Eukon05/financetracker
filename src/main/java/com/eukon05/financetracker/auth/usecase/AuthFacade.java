package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public
class AuthFacade {

    private final UserFacade userFacade;
    private final EmailFacade emailFacade;
    private final LoginUseCase loginUseCase;
    private final RefreshUseCase refreshUseCase;
    private final ForgotUseCase forgotUseCase;

    public Map<String, String> login(LoginDTO dto, String issuerUrl) {
        return loginUseCase.login(dto, issuerUrl);
    }

    public Map<String, String> refresh(String authHeader, String issuerUrl) {
        return refreshUseCase.refresh(authHeader, issuerUrl);
    }

    public void forgot(String email, String rootUrl) {
        User user = userFacade.getUserByEmailOrThrow(email);
        Token token = forgotUseCase.forgot(user);
        emailFacade.sendPasswordResetConfirmationEmail(user.getUsername(), email, token.getValue(), rootUrl, token.getId());
    }


}
