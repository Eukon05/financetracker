package com.eukon05.financetracker.service.jwt.usecase.generateRefreshToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
class GenerateRefreshTokenUseCaseImpl implements GenerateRefreshTokenUseCase {

    @Value("${jwt.refresh.lifetimeMinutes}")
    private int jwtRefreshLifetimeMinutes;

    private final Algorithm refreshAlgorithm;

    public GenerateRefreshTokenUseCaseImpl(@Value("${jwt.refresh.secret}") String jwtRefreshSecret) {
        refreshAlgorithm = Algorithm.HMAC256(jwtRefreshSecret);
    }

    @Override
    public String generateRefreshToken(String username, String issuerUrl) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(issuerUrl)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(jwtRefreshLifetimeMinutes, ChronoUnit.MINUTES))
                .sign(refreshAlgorithm);
    }


}
