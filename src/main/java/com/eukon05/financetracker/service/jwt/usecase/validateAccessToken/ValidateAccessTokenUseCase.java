package com.eukon05.financetracker.service.jwt.usecase.validateAccessToken;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ValidateAccessTokenUseCase {
    DecodedJWT validateAccessToken(String token);
}
