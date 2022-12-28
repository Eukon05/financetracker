package com.eukon05.financetracker.jwt.usecase;

interface GenerateRefreshTokenUseCase {
    String generateRefreshToken(String username, String issuerUrl);
}
