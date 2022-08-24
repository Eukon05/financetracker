package com.eukon05.financetracker.jwt.usecase;

import com.auth0.jwt.interfaces.DecodedJWT;

interface ValidateRefreshTokenUseCase {
    DecodedJWT validateRefreshToken(String token);
}
