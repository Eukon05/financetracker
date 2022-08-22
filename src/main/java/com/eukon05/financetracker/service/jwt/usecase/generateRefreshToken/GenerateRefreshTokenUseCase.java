package com.eukon05.financetracker.service.jwt.usecase.generateRefreshToken;

public interface GenerateRefreshTokenUseCase {
    String generateRefreshToken(String username, String issuerUrl);
}
