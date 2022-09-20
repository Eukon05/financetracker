package com.eukon05.financetracker.auth;

import com.eukon05.financetracker.auth.dto.ForgotDTO;
import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.auth.usecase.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    Map<String, String> login(@RequestBody @Valid LoginDTO dto, HttpServletRequest request) {
        return authFacade.login(dto, request.getRequestURL().toString());
    }

    @PostMapping("/refresh")
    Map<String, String> refresh(HttpServletRequest request) {
        return authFacade.refresh(request.getHeader(AUTHORIZATION), request.getRequestURL().toString());
    }

    @PostMapping("/forgot")
    void forgotPassword(@Valid @RequestBody ForgotDTO dto, HttpServletRequest request) {
        authFacade.forgot(dto.email(), request.getRequestURL().toString().replace("/forgot", ""));
    }


}
