package com.eukon05.financetracker.token;

import com.eukon05.financetracker.token.usecase.TokenFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class TokenController {

    private final TokenFacade tokenFacade;

    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam String token) {
        tokenFacade.confirmRegistration(token);
    }

}
