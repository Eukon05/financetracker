package com.eukon05.financetracker.controller;

import com.eukon05.financetracker.dto.LoginDTO;
import com.eukon05.financetracker.service.security.SecurityFacade;
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
public class SecurityController {

    private final SecurityFacade securityFacade;

    @PostMapping("/login")
    Map<String, String> login(@RequestBody @Valid LoginDTO dto, HttpServletRequest request) {
        return securityFacade.login(dto, request.getRequestURL().toString());
    }

    @PostMapping("/refresh")
    Map<String, String> refresh(HttpServletRequest request) {
        return securityFacade.refresh(request.getHeader(AUTHORIZATION), request.getRequestURL().toString());
    }


}
