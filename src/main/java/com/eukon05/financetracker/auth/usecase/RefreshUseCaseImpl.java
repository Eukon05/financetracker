package com.eukon05.financetracker.auth.usecase;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.eukon05.financetracker.auth.exception.InvalidTokenException;
import com.eukon05.financetracker.auth.exception.MissingTokenException;
import com.eukon05.financetracker.jwt.usecase.JwtFacade;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.eukon05.financetracker.auth.AuthConstants.*;

@RequiredArgsConstructor
@Service
class RefreshUseCaseImpl implements RefreshUseCase {

    private final JwtFacade jwtFacade;
    private final UserFacade userFacade;

    public Map<String, String> refresh(String auth, String issuer) {
        if (auth == null || !auth.startsWith(TOKEN_PREFIX.getValue())) {
            throw new MissingTokenException();
        }

        DecodedJWT jwt;
        try {
            jwt = jwtFacade.validateRefreshToken(auth.substring(TOKEN_PREFIX.getValue().length()));
        } catch (Exception ex) {
            throw new InvalidTokenException();
        }

        //This line isn't required in the "login()" method, since authenticationManager automatically checks if the user exists
        //Here, however, we need to check if the refresh token, even if valid, corresponds to a registered user
        //It also refreshes the token with any new roles added to the user between refreshes
        UserDetails userDetails = userFacade.getUserByUsernameOrThrow(jwt.getSubject());

        Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN.getValue(), jwtFacade.generateAccessToken(userDetails, issuer));
        tokens.put(REFRESH_TOKEN.getValue(), auth.replace(TOKEN_PREFIX.getValue(), ""));

        return tokens;
    }

}
