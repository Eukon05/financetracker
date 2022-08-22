package com.eukon05.financetracker.controller;

import com.eukon05.financetracker.service.token.TokenFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenFacade tokenFacade;

    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam String token) {
        tokenFacade.confirmRegistration(token);
    }

}
