package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.usecase.TransactionFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionTests extends AbstractIntegrationTest {

    private final CreateTransactionDTO dto = new CreateTransactionDTO(1L, "transaction", new BigDecimal("11.5"), TransactionType.EXPENSE);

    @Autowired
    private TransactionFacade facade;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
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

    @Test
    void should_delete_a_transaction() throws Exception {
        String token = utils.getTestAccessToken();
        createTestTransaction();

        mockMvc.perform(delete("/transactions/1")
                        .header(AUTHORIZATION, token))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_a_transaction() throws Exception {
        String token = utils.getTestAccessToken();
        createTestTransaction();

        mockMvc.perform(get("/transactions/1")
                        .header(AUTHORIZATION, token))
                .andExpectAll(status().isOk(), jsonPath("$.name").value(dto.name()));
    }

    private void createTestTransaction() {
        facade.createTransaction(utils.getLoginDTO().username(), dto);
        utils.flushDatabase();
    }

}
