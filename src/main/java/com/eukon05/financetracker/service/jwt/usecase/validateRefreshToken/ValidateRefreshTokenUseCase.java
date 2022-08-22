package com.eukon05.financetracker.service.jwt.usecase.validateRefreshToken;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ValidateRefreshTokenUseCase {
    DecodedJWT validateRefreshToken(String token);
}
