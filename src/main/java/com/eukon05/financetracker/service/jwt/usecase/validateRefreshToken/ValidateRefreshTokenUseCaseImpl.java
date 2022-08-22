package com.eukon05.financetracker.service.jwt.usecase.validateRefreshToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class ValidateRefreshTokenUseCaseImpl implements ValidateRefreshTokenUseCase {

    private final Algorithm refreshAlgorithm;

    public ValidateRefreshTokenUseCaseImpl(@Value("${jwt.refresh.secret}") String jwtRefreshSecret) {
        refreshAlgorithm = Algorithm.HMAC256(jwtRefreshSecret);
    }

    @Override
    public DecodedJWT validateRefreshToken(String token) {
        return JWT.require(refreshAlgorithm).build().verify(token);
    }

}
