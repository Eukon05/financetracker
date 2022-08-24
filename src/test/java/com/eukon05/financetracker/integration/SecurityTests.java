package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.user.UserRepository;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class SecurityTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MockMvc mockMvc;
    private final UserRepository repository;

    @Autowired
    SecurityTests(MockMvc mockMvc, UserRepository repository) {
        this.mockMvc = mockMvc;
        this.repository = repository;
    }

    RegisterDTO createRegisterRequest() {
        return new RegisterDTO("test",
                "test1234",
                "test1234",
                "test",
                "test",
                "test@test.com");
    }

    LoginDTO createLoginRequest() {
        return new LoginDTO("test", "test1234");
    }

    @Test
    void should_not_let_disabled_user_login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(objectMapper.writeValueAsString(createRegisterRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(createLoginRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void should_let_enabled_user_login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(objectMapper.writeValueAsString(createRegisterRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        repository.findById(1L).ifPresent(user -> {
            user.setEnabled(true);
            repository.save(user);
        });

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(createLoginRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), jsonPath("$.access_token").isNotEmpty(), jsonPath("$.refresh_token").isNotEmpty());
    }

}
