package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.auth.usecase.AuthFacade;
import com.eukon05.financetracker.user.UserRepository;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;

import static com.eukon05.financetracker.auth.AuthFinals.ACCESS_TOKEN;

@TestComponent
@RequiredArgsConstructor
class IntegrationTestsUtils {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final AuthFacade authFacade;

    @Getter
    private final RegisterDTO registerDTO = new RegisterDTO("test", "test1234", "test1234", "test@test.com");

    @Getter
    private final LoginDTO loginDTO = new LoginDTO(registerDTO.username(), registerDTO.password());

    void registerTestUser() {
        userFacade.createUser(registerDTO, "");
        userRepository.findById(1L).ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
    }


    String getTestAccessToken() {
        return authFacade.login(loginDTO, "").get(ACCESS_TOKEN);
    }
}
