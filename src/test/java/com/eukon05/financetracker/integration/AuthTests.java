package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthTests extends AbstractIntegrationTest {

    @Autowired
    private UserRepository repository;

    @Test
    void should_not_let_disabled_user_login() throws Exception {
        disableTestUser();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(utils.getLoginDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_let_enabled_user_login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(utils.getLoginDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), jsonPath("$.access_token").isNotEmpty(), jsonPath("$.refresh_token").isNotEmpty());
    }

    private void disableTestUser() {
        repository.findById(1L).ifPresent(user -> {
            user.setEnabled(false);
            repository.save(user);
        });
    }

}
