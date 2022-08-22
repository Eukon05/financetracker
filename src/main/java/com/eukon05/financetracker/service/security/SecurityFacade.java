package com.eukon05.financetracker.service.security;

import com.eukon05.financetracker.dto.LoginDTO;
import com.eukon05.financetracker.service.security.usecase.login.LoginUseCase;
import com.eukon05.financetracker.service.security.usecase.refresh.RefreshUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public
class SecurityFacade {

    private final LoginUseCase loginUseCase;
    private final RefreshUseCase refreshUseCase;

    public Map<String, String> login(LoginDTO dto, String issuerUrl) {
        return loginUseCase.login(dto, issuerUrl);
    }

    public Map<String, String> refresh(String authHeader, String issuerUrl) {
        return refreshUseCase.refresh(authHeader, issuerUrl);
    }


}
