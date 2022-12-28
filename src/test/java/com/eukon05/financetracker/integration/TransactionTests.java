package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Optional;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionTests extends AbstractIntegrationTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private TransactionCategoryRepository transactionCategoryRepository;

    @Test
    void should_create_a_transaction() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        Mockito.when(transactionCategoryRepository.findById(0L)).thenReturn(Optional.of(defaultCategory));

        CreateTransactionDTO dto = new CreateTransactionDTO(1L, "transaction", new BigDecimal("11.5"), 0);

        mockMvc.perform(post("/transactions")
                        .header(AUTHORIZATION, utils.getDefaultToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        assertTrue(testWallet.getTransactions().size() > 1);
        testWallet.getTransactions().clear();
        testWallet.getTransactions().add(testTransaction);
    }

    @Test
    void should_delete_a_transaction() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(testTransaction));


        mockMvc.perform(delete("/transactions/1")
                        .header(AUTHORIZATION, utils.getDefaultToken()))
                .andExpect(status().isOk());

        Mockito.verify(transactionRepository).delete(testTransaction);
    }

    @Test
    void should_get_a_transaction() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(testTransaction));

        getTestTransaction().andExpectAll(status().isOk(), jsonPath("$.name").value(testTransaction.getName()));
    }

    @Test
    void should_edit_a_transaction() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(testTransaction));
        Mockito.when(transactionCategoryRepository.findById(2L)).thenReturn(Optional.of(testExpenseCategory));

        EditTransactionDTO dto = new EditTransactionDTO("newname", BigDecimal.valueOf(-5431), 2);

        mockMvc.perform(put("/transactions/1")
                        .header(AUTHORIZATION, utils.getDefaultToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpectAll(status().isOk());

        assertEquals(dto.name(), testTransaction.getName());
        assertEquals(testExpenseCategory, testTransaction.getCategory());
        assertEquals(dto.value(), testTransaction.getValue());
    }

    @Test
    void should_not_edit_transaction() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(testTransaction));
        Mockito.when(transactionCategoryRepository.findById(1L)).thenReturn(Optional.of(testIncomeCategory));

        EditTransactionDTO dto = new EditTransactionDTO(null, BigDecimal.valueOf(-5431), 1);

        mockMvc.perform(put("/transactions/1")
                        .header(AUTHORIZATION, utils.getDefaultToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpectAll(status().isConflict());
    }

    private ResultActions getTestTransaction() throws Exception {
        return mockMvc.perform(get("/transactions/1")
                .header(AUTHORIZATION, utils.getDefaultToken()));
    }

}
