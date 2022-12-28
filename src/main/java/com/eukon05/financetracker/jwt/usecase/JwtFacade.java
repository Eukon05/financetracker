package com.eukon05.financetracker.jwt.usecase;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtFacade {

    private final GenerateRefreshTokenUseCase generateRefreshTokenUseCase;
    private final GenerateAccessTokenUseCase generateAccessTokenUseCase;
    private final ValidateAccessTokenUseCase validateAccessTokenUseCase;
    private final ValidateRefreshTokenUseCase validateRefreshTokenUseCase;

    public DecodedJWT validateRefreshToken(String token) {
        return validateRefreshTokenUseCase.validateRefreshToken(token);
    }

    public DecodedJWT validateAccessToken(String token) {
        return validateAccessTokenUseCase.validateAccessToken(token);
    }

    public String generateAccessToken(UserDetails userDetails, String issuer) {
        return generateAccessTokenUseCase.generateAccessToken(userDetails, issuer);
    }

    public String generateRefreshToken(String username, String issuer) {
        return generateRefreshTokenUseCase.generateRefreshToken(username, issuer);
    }


}
