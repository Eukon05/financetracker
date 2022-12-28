package com.eukon05.financetracker.integration;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.testUser;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserInfoTests extends AbstractIntegrationTest {

    @Test
    void should_get_user_info() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/users/me").header(AUTHORIZATION, utils.getDefaultToken()))
                .andExpectAll(status().isOk(),
                        jsonPath("$.username").value(testUser.getUsername()),
                        jsonPath("$.email").value(testUser.getEmail()),
                        jsonPath("$.roles.[0]").value("USER"));
    }

}
