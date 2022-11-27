package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.user.UserRepository;
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
    private UserRepository userRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void should_create_a_transaction() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        CreateTransactionDTO dto = new CreateTransactionDTO(1L, "transaction", new BigDecimal("11.5"), TransactionType.EXPENSE);

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

        EditTransactionDTO dto = new EditTransactionDTO("newname", BigDecimal.valueOf(5431), TransactionType.EXPENSE);

        mockMvc.perform(put("/transactions/1")
                        .header(AUTHORIZATION, utils.getDefaultToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpectAll(status().isOk());

        assertEquals(dto.name(), testTransaction.getName());
        assertEquals(dto.type(), testTransaction.getType());
        assertEquals(dto.value(), testTransaction.getValue());
    }

    private ResultActions getTestTransaction() throws Exception {
        return mockMvc.perform(get("/transactions/1")
                .header(AUTHORIZATION, utils.getDefaultToken()));
    }

}
