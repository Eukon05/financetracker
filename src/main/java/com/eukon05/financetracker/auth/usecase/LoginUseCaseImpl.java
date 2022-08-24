package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.jwt.usecase.JwtFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.eukon05.financetracker.auth.AuthFinals.ACCESS_TOKEN;
import static com.eukon05.financetracker.auth.AuthFinals.REFRESH_TOKEN;

@RequiredArgsConstructor
@Service
class LoginUseCaseImpl implements LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtFacade jwtFacade;

    public Map<String, String> login(LoginDTO dto, String issuer) {
        UserDetails userDetails = (UserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password())).getPrincipal();

        Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN, jwtFacade.generateAccessToken(userDetails, issuer));
        tokens.put(REFRESH_TOKEN, jwtFacade.generateRefreshToken(dto.username(), issuer));

        return tokens;
    }

}
