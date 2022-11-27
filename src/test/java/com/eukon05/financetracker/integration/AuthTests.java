package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.testUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthTests extends AbstractIntegrationTest {

    @MockBean
    private UserRepository repository;

    private static final LoginDTO loginDTO = new LoginDTO("test", "test");
    private static final User disabledUser = new User();

    static {
        disabledUser.setPassword(testUser.getPassword());
    }


    @Test
    void should_not_let_disabled_user_login() throws Exception {
        Mockito.when(repository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(disabledUser));
        makeLoginRequest().andExpect(status().isUnauthorized());
    }

    @Test
    void should_let_enabled_user_login() throws Exception {
        Mockito.when(repository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        makeLoginRequest().andExpectAll(status().isOk(), jsonPath("$.access_token").isNotEmpty(), jsonPath("$.refresh_token").isNotEmpty());
    }

    private ResultActions makeLoginRequest() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .content(objectMapper.writeValueAsString(loginDTO))
                .contentType(MediaType.APPLICATION_JSON));
    }

}
