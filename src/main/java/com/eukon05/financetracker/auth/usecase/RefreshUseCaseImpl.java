package com.eukon05.financetracker.auth.usecase;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.eukon05.financetracker.jwt.usecase.JwtFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.eukon05.financetracker.auth.AuthFinals.*;

@RequiredArgsConstructor
@Service
class RefreshUseCaseImpl implements RefreshUseCase {

    private final JwtFacade jwtFacade;
    private final UserDetailsService userDetailsService;

    public Map<String, String> refresh(String auth, String issuer) {
        if (Optional.ofNullable(auth).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!auth.startsWith(TOKEN_PREFIX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        DecodedJWT jwt = jwtFacade.validateRefreshToken(auth.substring(TOKEN_PREFIX.length()));

        //This line isn't required in the "login()" method, since authenticationManager automatically checks if the user exists
        //Here, however, we need to check if the refresh token, even if valid, corresponds to a registered user
        //It also refreshes the token with any new roles added to the user between refreshes
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwt.getSubject());

        Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN, jwtFacade.generateAccessToken(userDetails, issuer));
        tokens.put(REFRESH_TOKEN, auth.replace(TOKEN_PREFIX, ""));

        return tokens;
    }

}
