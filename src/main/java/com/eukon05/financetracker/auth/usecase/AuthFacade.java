package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.auth.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public
class AuthFacade {

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
        forgotUseCase.forgot(email, rootUrl);
    }


}
