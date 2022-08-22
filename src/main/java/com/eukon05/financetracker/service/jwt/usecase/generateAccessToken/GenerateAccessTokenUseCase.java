package com.eukon05.financetracker.service.jwt.usecase.generateAccessToken;

import org.springframework.security.core.userdetails.UserDetails;

public interface GenerateAccessTokenUseCase {
    String generateAccessToken(UserDetails userDetails, String issuerUrl);
}
