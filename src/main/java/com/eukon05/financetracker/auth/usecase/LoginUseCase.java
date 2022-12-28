package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.auth.dto.LoginDTO;

import java.util.Map;

interface LoginUseCase {
    Map<String, String> login(LoginDTO dto, String issuer);
}
