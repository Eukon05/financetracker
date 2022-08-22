package com.eukon05.financetracker.service.security.usecase.login;

import com.eukon05.financetracker.dto.LoginDTO;
import com.eukon05.financetracker.service.jwt.JwtFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.eukon05.financetracker.common.SecurityFinals.ACCESS_TOKEN;
import static com.eukon05.financetracker.common.SecurityFinals.REFRESH_TOKEN;

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
