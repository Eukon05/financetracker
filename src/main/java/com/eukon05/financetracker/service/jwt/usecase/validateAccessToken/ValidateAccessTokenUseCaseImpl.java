package com.eukon05.financetracker.service.jwt.usecase.validateAccessToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class ValidateAccessTokenUseCaseImpl implements ValidateAccessTokenUseCase {

    private final Algorithm accessAlgorithm;

    public ValidateAccessTokenUseCaseImpl(@Value("${jwt.access.secret}") String jwtAccessSecret) {
        accessAlgorithm = Algorithm.HMAC256(jwtAccessSecret);
    }

    @Override
    public DecodedJWT validateAccessToken(String token) {
        return JWT.require(accessAlgorithm).build().verify(token);
    }

}
