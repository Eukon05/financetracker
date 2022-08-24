package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
class IntegrationTestsUtils {

    private final UserFacade userFacade;

    RegisterDTO createRegisterDTO() {
        return new RegisterDTO("test",
                "test1234",
                "test1234",
                "test",
                "test",
                "test@test.com");
    }

    void registerTestUser() {
        userFacade.createUser(createRegisterDTO(), "");
    }

}
