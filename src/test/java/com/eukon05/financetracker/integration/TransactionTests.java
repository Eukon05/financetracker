package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
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

import java.math.BigDecimal;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@Import({IntegrationTestsUtils.class, IntegrationTestsConfiguration.class})
class TransactionTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IntegrationTestsUtils utils;

    private final CreateTransactionDTO dto = new CreateTransactionDTO(1L, "transaction", new BigDecimal("11.5"), TransactionType.EXPENSE);

    @BeforeEach
    void setUp() {
        utils.registerTestUser();
        utils.createTestWallet();
    }

    @Test
    void should_create_a_transaction() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/transactions")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

}
