package com.eukon05.financetracker.service.jwt.usecase.generateAccessToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
class GenerateAccessTokenUseCaseImpl implements GenerateAccessTokenUseCase {

    @Value("${jwt.access.lifetimeMinutes}")
    private int jwtAccessLifetimeMinutes;

    private final Algorithm accessAlgorithm;

    public GenerateAccessTokenUseCaseImpl(@Value("${jwt.access.secret}") String jwtAccessSecret) {
        accessAlgorithm = Algorithm.HMAC256(jwtAccessSecret);
    }

    @Override
    public String generateAccessToken(UserDetails userDetails, String issuerUrl) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withArrayClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withIssuer(issuerUrl)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(jwtAccessLifetimeMinutes, ChronoUnit.MINUTES))
                .sign(accessAlgorithm);
    }

}
