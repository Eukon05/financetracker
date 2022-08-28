package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.wallet.dto.CreateEditWalletDTO;
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
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@Import({IntegrationTestsUtils.class, IntegrationTestsConfiguration.class})
class WalletTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IntegrationTestsUtils utils;

    private static final CreateEditWalletDTO dto = new CreateEditWalletDTO("test wallet");

    @BeforeEach
    void setUp() {
        utils.registerTestUser();
    }

    @Test
    void should_create_a_new_wallet() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());
    }

    @Test
    void should_validate_wallet_already_exists() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isConflict());
    }

    @Test
    void should_delete_wallet() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/wallets/1")
                        .header(AUTHORIZATION, token))
                .andExpect(status().isOk());
    }

    @Test
    void should_edit_wallet() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/wallets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateEditWalletDTO("new name")))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isOk());
    }

    @Test
    void should_not_edit_wallet() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateEditWalletDTO("new name")))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/wallets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateEditWalletDTO("new name")))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_user_wallets() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/wallets")
                        .header(AUTHORIZATION, token))
                .andExpectAll(status().isOk(), jsonPath("$.[0].name").value("test wallet"));
    }

}
