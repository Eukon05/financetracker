package com.eukon05.financetracker.service.security.usecase.login;

import com.eukon05.financetracker.dto.LoginDTO;

import java.util.Map;

public interface LoginUseCase {
    Map<String, String> login(LoginDTO dto, String issuer);
}
