package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.testUser;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserInfoTests extends AbstractIntegrationTest {

    @MockBean
    private UserRepository repository;

    @Test
    void should_get_user_info() throws Exception {
        Mockito.when(repository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/users/me").header(AUTHORIZATION, utils.getDefaultToken()))
                .andExpectAll(status().isOk(),
                        jsonPath("$.username").value(testUser.getUsername()),
                        jsonPath("$.email").value(testUser.getEmail()),
                        jsonPath("$.roles.[0]").value("USER"));
    }

}
