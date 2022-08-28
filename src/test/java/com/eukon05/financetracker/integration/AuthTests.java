package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import({IntegrationTestsUtils.class, IntegrationTestsConfiguration.class})
class AuthTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IntegrationTestsUtils utils;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        utils.registerTestUser();
    }

    @Test
    void should_not_let_disabled_user_login() throws Exception {
        disableTestUser();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(utils.getLoginDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
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
