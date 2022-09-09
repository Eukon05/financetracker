package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.wallet.dto.CreateEditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WalletTests extends AbstractIntegrationTest {

    @Autowired
    private WalletFacade walletFacade;

    private static final CreateEditWalletDTO dto = new CreateEditWalletDTO("test wallet");

    @Test
    void should_create_a_new_wallet() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isCreated());

        assertFalse(getTestUserWallets().isEmpty());
    }

    @Test
    void should_validate_wallet_already_exists() throws Exception {
        String token = utils.getTestAccessToken();
        createTestWallet();

        mockMvc.perform(post("/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isConflict());
    }

    @Test
    void should_delete_wallet() throws Exception {
        String token = utils.getTestAccessToken();
        createTestWallet();

        assertFalse(getTestUserWallets().isEmpty());

        mockMvc.perform(delete("/wallets/1")
                        .header(AUTHORIZATION, token))
                .andExpect(status().isOk());

        assertTrue(getTestUserWallets().isEmpty());
    }

    @Test
    void should_edit_wallet() throws Exception {
        String token = utils.getTestAccessToken();
        createTestWallet();

        mockMvc.perform(put("/wallets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateEditWalletDTO("new name")))
                        .header(AUTHORIZATION, token))
                .andExpect(status().isOk());

        assertEquals("new name", getTestUserWallets().get(0).name());
    }

    @Test
    void should_not_edit_wallet() throws Exception {
        String token = utils.getTestAccessToken();
        createTestWallet();

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
        createTestWallet();

        mockMvc.perform(get("/wallets")
                        .header(AUTHORIZATION, token))
                .andExpectAll(status().isOk(), jsonPath("$.[0].name").value(dto.name()));
    }

    @Test
    void should_get_wallet_transactions() throws Exception {
        String token = utils.getTestAccessToken();
        createTestWallet();

        mockMvc.perform(post("/transactions")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateTransactionDTO(1L, "transaction", new BigDecimal("11.5"), TransactionType.EXPENSE))))
                .andExpect(status().isCreated());

        utils.flushDatabase();

        mockMvc.perform(get("/wallets/1/transactions")
                        .header(AUTHORIZATION, token))
                .andExpectAll(status().isOk(), jsonPath("$.content.[0].id").value(1));
    }

    private List<WalletDTO> getTestUserWallets() {
        return walletFacade.getUserWallets(utils.getRegisterDTO().username());
    }

    private void createTestWallet() {
        walletFacade.createWallet(utils.getLoginDTO().username(), dto.name());
        utils.flushDatabase();
    }

}
