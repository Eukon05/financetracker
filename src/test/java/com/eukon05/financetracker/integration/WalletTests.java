package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WalletTests extends AbstractIntegrationTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void should_create_a_new_wallet() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        CreateWalletDTO createDTO = new CreateWalletDTO("new wallet", "PLN");

        createTestWallet(createDTO).andExpect(status().isCreated());

        assertTrue(testUser.getWallets().size() > 1);
        testUser.getWallets().clear();
        testUser.getWallets().add(testWallet);
    }

    @Test
    void should_validate_wallet_already_exists() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));

        createTestWallet(new CreateWalletDTO(testWallet.getName(), testWallet.getCurrency())).andExpect(status().isConflict());
    }

    @Test
    void should_delete_wallet() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));

        mockMvc.perform(delete("/wallets/1")
                        .header(AUTHORIZATION, utils.getDefaultToken()))
                .andExpect(status().isOk());

        assertTrue(testUser.getWallets().isEmpty());
        testUser.getWallets().add(testWallet);
    }

    @Test
    void should_edit_wallet() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        EditWalletDTO editDTO = new EditWalletDTO("new name", "USD");

        editTestWallet(editDTO).andExpect(status().isOk());

        assertEquals(editDTO.name(), testWallet.getName());
        assertEquals(editDTO.currency(), testWallet.getCurrency());
    }

    @Test
    void should_not_edit_wallet() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setUser(testUser);
        wallet.setName("occupied");
        testUser.getWallets().add(wallet);

        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));

        editTestWallet(new EditWalletDTO(wallet.getName(), null)).andExpect(status().isConflict());

        testUser.getWallets().remove(wallet);
    }

    @Test
    void should_get_user_wallets() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/wallets")
                        .header(AUTHORIZATION, utils.getDefaultToken()))
                .andExpectAll(status().isOk(), jsonPath("$.[0].name").value(testWallet.getName()));
    }

    @Test
    void should_get_wallet_transactions() throws Exception {
        Mockito.when(userRepository.getUserByUsername(Mockito.any(String.class))).thenReturn(Optional.of(testUser));
        Mockito.when(transactionRepository.getTransactionsByWallet(Mockito.any(Wallet.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(testTransaction)));

        mockMvc.perform(get("/wallets/1/transactions")
                        .header(AUTHORIZATION, utils.getDefaultToken()))
                .andExpectAll(status().isOk(), jsonPath("$.content.[0].name").value(testTransaction.getName()));
    }

    private ResultActions createTestWallet(CreateWalletDTO dto) throws Exception {
        return mockMvc.perform(post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .header(AUTHORIZATION, utils.getDefaultToken()));
    }

    private ResultActions editTestWallet(EditWalletDTO dto) throws Exception {
        return mockMvc.perform(put("/wallets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .header(AUTHORIZATION, utils.getDefaultToken()));
    }

}
