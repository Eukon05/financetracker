package com.eukon05.financetracker.jwt.usecase;

import org.springframework.security.core.userdetails.UserDetails;

interface GenerateAccessTokenUseCase {
    String generateAccessToken(UserDetails userDetails, String issuerUrl);
}
