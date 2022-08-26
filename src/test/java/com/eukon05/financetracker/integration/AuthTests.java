package com.eukon05.financetracker.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
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
@Import({IntegrationTestsUtils.class, IntegrationTestsConfiguration.class})
class AuthTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IntegrationTestsUtils utils;

    @Test
    void should_not_let_disabled_user_login() throws Exception {
        utils.registerTestUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(utils.createLoginDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void should_let_enabled_user_login() throws Exception {
        utils.registerTestUser();
        utils.enableTestUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(objectMapper.writeValueAsString(utils.createLoginDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), jsonPath("$.access_token").isNotEmpty(), jsonPath("$.refresh_token").isNotEmpty());
    }

}
